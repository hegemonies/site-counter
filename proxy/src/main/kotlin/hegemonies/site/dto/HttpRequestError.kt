package hegemonies.site.dto

import kotlinx.serialization.Serializable

@Serializable
data class HttpRequestError(
    val message: String,
)
