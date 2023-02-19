package site.hegemonies.sitecounter.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "proxy")
data class ProxyProperties(
    val token: String,
    val enabled: Boolean,
    val address: String,
    val limit: Int
)
