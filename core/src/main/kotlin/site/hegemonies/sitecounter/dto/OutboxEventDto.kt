package site.hegemonies.sitecounter.dto

data class OutboxEventDto(
    val clientAddress: String,
    val uri: String,
    val headers: Map<String, String>,
)
