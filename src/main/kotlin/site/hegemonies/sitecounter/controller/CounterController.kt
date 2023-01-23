package site.hegemonies.sitecounter.controller

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import site.hegemonies.sitecounter.dto.OutboxEventDto
import site.hegemonies.sitecounter.service.IOutboxService

@RestController
class CounterController(
    private val outboxService: IOutboxService
) {

    @GetMapping("/api/v1/counter/increment")
    suspend fun increment(@RequestHeader headers: Map<String, String>) {
        val clientAddress = ""
        val requestUri = ""
        val event = OutboxEventDto(
            clientAddress = clientAddress,
            uri = requestUri,
            headers = headers
        )
        logger.info { "Receive request from '$clientAddress' with headers=$headers" }
        outboxService.sendEvent(event)
    }

    companion object : KLogging()
}
