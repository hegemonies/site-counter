package hegemonies.site.dto.incrementcounter

import kotlinx.serialization.Serializable

@Serializable
data class IncrementCountRequest(
    val token: String,
    val url: String,
)
