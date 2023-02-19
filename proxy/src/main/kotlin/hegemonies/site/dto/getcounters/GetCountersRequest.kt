package hegemonies.site.dto.getcounters

import kotlinx.serialization.Serializable

@Serializable
data class GetCountersRequest(
    val token: String,
    val lastId: Long,
    val limit: Int? = null
)
