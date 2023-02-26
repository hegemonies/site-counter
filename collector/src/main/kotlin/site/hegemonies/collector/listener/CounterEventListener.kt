package site.hegemonies.collector.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.runBlocking
import mu.KLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import site.hegemonies.collector.constant.KafkaConstants
import site.hegemonies.collector.dto.CounterEventDto
import site.hegemonies.collector.service.CounterService

@Service
class CounterEventListener(
    private val counterService: CounterService,
    private val jsonMapper: ObjectMapper,
) {

    @KafkaListener(topics = [KafkaConstants.COUNTER_TOPIC_NAME], groupId = KafkaConstants.COUNTER_TOPIC_GROUP_ID)
    fun listen(@Payload message: String): Unit = runBlocking {
        val dto = message.toDto()
        counterService.saveCounter(dto)
    }

    private fun String.toDto(): CounterEventDto =
        jsonMapper.readValue(this)

    private companion object : KLogging()
}
