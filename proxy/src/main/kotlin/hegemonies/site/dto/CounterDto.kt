package hegemonies.site.dto

import hegemonies.site.model.Counter
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class CounterDto(
    val id: Long,
    val clientAddress: String,
    val uri: String,
    val headers: Map<String, String>,
    val createdAt: Long,
) {
    companion object {
        fun fromResultRaw(raw: ResultRow) =
            CounterDto(
                id = raw[Counter.id],
                clientAddress = raw[Counter.clientAddress],
                uri = raw[Counter.uri],
                headers = Json.decodeFromString(raw[Counter.headers]),
                createdAt = raw[Counter.createdAt]
            )
    }
}
