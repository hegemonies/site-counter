package site.hegemonies.sitecounter.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import site.hegemonies.sitecounter.configuration.properties.KafkaProperties
import site.hegemonies.sitecounter.dto.OutboxEventDto
import site.hegemonies.sitecounter.model.OutboxMessage
import site.hegemonies.sitecounter.repository.OutboxRepository
import java.time.Instant
import java.util.UUID

@Service
class OutboxService(
    private val outboxRepository: OutboxRepository,
    private val jsonMapper: ObjectMapper,
    private val kafkaProperties: KafkaProperties
) : IOutboxService {

    override suspend fun sendEvent(outboxEventDto: OutboxEventDto) {
        val outboxMessage = OutboxMessage(
            createdAt = Instant.now(),
            topic = kafkaProperties.siteCounterTopic,
            key = UUID.randomUUID().toString(),
            message = jsonMapper.writeValueAsString(outboxEventDto),
        )
        outboxRepository.save(outboxMessage)
    }
}
