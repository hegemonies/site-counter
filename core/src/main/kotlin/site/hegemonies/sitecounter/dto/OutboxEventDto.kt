package site.hegemonies.sitecounter.dto

data class OutboxEventDto(
    val id: Long,
    val clientAddress: String,
    val uri: String,
    val headers: Map<String, String>,
    val createdAt: Long,
)
