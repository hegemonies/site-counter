package site.hegemonies.collector.configuration

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.KredsClient
import io.github.crackthecodeabhi.kreds.connection.KredsClientConfig
import io.github.crackthecodeabhi.kreds.connection.newClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import site.hegemonies.collector.configuration.properties.RedisProperties

@Configuration
class RedisConfiguration(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redisClient(): KredsClient =
        newClient(Endpoint(redisProperties.host, redisProperties.port))
}
