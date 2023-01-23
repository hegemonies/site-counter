package site.hegemonies.sitecounter.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "kafka-custom")
data class KafkaProperties(
    val siteCounterTopic: String = ""
)
