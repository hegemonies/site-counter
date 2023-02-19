package site.hegemonies.sitecounter.dto

import kotlinx.serialization.Serializable

@Serializable
data class CounterDto(
    val id: Long,
    val clientAddress: String,
    val uri: String,
    val headers: Map<String, String>,
)
