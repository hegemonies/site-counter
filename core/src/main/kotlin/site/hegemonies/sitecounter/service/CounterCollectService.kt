package site.hegemonies.sitecounter.service

import hegemonies.site.dto.getcounters.GetCountersRequest
import hegemonies.site.dto.getcounters.GetCountersResponse
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import site.hegemonies.sitecounter.configuration.properties.ProxyProperties
import site.hegemonies.sitecounter.dto.OutboxEventDto
import site.hegemonies.sitecounter.model.ProxyCollectLastId
import site.hegemonies.sitecounter.repository.ProxyCollectLastIdRepository

@Service
class CounterCollectService(
    private val proxyProperties: ProxyProperties,
    private val proxyCollectLastIdRepository: ProxyCollectLastIdRepository,
    private val outboxService: IOutboxService,
) : ICounterCollectService {

    private val webClient by lazy {
        WebClient.builder()
            .baseUrl(proxyProperties.address)
            .build()
    }

    @Transactional
    override suspend fun collect() {
        val lastCounterIdEntity = proxyCollectLastIdRepository.findAll().toList().firstOrNull()
        val lastId = lastCounterIdEntity?.lastId ?: 0L

        val request = GetCountersRequest(
            token = proxyProperties.token,
            lastId = lastId,
            limit = proxyProperties.limit
        )

        val response = webClient.post()
            .bodyValue(request)
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono { response ->
                if (response.statusCode().is2xxSuccessful) {
                    response.bodyToMono(GetCountersResponse::class.java)
                } else {
                    response.createError()
                }
            }.awaitFirst()

        response.counters.asFlow().map { counter ->
            OutboxEventDto(
                clientAddress = counter.clientAddress,
                uri = counter.uri,
                headers = counter.headers
            )
        }.collect { outboxEvent ->
            outboxService.sendEvent(outboxEvent)
        }

        if (response.counters.isNotEmpty()) {
            lastCounterIdEntity?.also { entity ->
                proxyCollectLastIdRepository.save(entity.copy(lastId = response.counters.last().id))
            } ?: run {
                proxyCollectLastIdRepository.save(ProxyCollectLastId(lastId = response.counters.last().id))
            }
        }
    }
}
