package site.hegemonies.collector

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.stereotype.Component
import site.hegemonies.collector.dto.CounterEventDto
import site.hegemonies.collector.service.CounterService

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableKafka
class CollectorApplication

fun main(args: Array<String>) {
	runApplication<CollectorApplication>(*args)
}


@Component
class Foo(
	private val counterService: CounterService
) {

	@EventListener(ApplicationReadyEvent::class)
	fun foo() = runBlocking {
		val event = CounterEventDto(
			id = 1,
			clientAddress = "1.2.3.4",
			uri = "https://hegemonies.site/cv",
			headers = mapOf("os" to "macOS"),
			createdAt = System.currentTimeMillis()
		)

		counterService.saveCounter(event)
	}
}
