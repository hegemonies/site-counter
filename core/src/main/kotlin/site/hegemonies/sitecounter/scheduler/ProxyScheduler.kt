package site.hegemonies.sitecounter.scheduler

import kotlinx.coroutines.runBlocking
import mu.KLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import site.hegemonies.sitecounter.configuration.properties.ProxyProperties
import site.hegemonies.sitecounter.service.ICounterCollectService
import kotlin.system.measureTimeMillis

/**
 * Забирает счетчики из прокси сервиса.
 */
@Component
class ProxyScheduler(
    private val counterCollectService: ICounterCollectService,
    private val proxyProperties: ProxyProperties
) {

    init {
        logger.info { "PROXY_TOKEN=${proxyProperties.token}" }
    }

    @Scheduled(fixedDelay = 60000)
    fun collectCounters() {
        if (proxyProperties.enabled) {
            logger.info { "Start proxy scheduler" }
            val elapsed = measureTimeMillis {
                runBlocking { counterCollectService.collect() }
            }
            logger.info { "Finish proxy scheduler for $elapsed ms" }
        }
    }

    companion object : KLogging()
}
