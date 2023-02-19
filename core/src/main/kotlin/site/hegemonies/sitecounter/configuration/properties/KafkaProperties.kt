package site.hegemonies.sitecounter.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka-custom")
data class KafkaProperties(
    val siteCounterTopic: String = ""
)
