package site.hegemonies.collector.dto

import java.io.Serializable

data class CounterEventDto(
    val id: Long,
    val clientAddress: String,
    val uri: String,
    val headers: Map<String, String>,
    val createdAt: Long,
) : Serializable
