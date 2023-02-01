package site.hegemonies.sitecounter.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mu.KLogging
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import site.hegemonies.sitecounter.dto.OutboxEventDto
import site.hegemonies.sitecounter.service.IOutboxService

@RestController
class CounterController(
    private val outboxService: IOutboxService,
    private val coroutineScope: CoroutineScope,
) {

    @GetMapping("/api/v1/counter/increment")
    suspend fun increment(@RequestHeader headers: Map<String, String>, request: ServerHttpRequest) {
        coroutineScope.launch {
            logger.info { "Request: $request" }

            val clientAddress = request.remoteAddress?.address?.toString() ?: ""
            val requestUri = request.uri.toString()
            val event = OutboxEventDto(
                clientAddress = clientAddress,
                uri = requestUri,
                headers = headers
            )

            logger.info { "Receive request from '$clientAddress' with headers=$headers" }

            outboxService.sendEvent(event)
        }
    }

    companion object : KLogging()
}
