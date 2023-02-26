package site.hegemonies.collector.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "redis")
class RedisProperties(
    val host: String = "localhost",
    val port: Int = 6379,
)
