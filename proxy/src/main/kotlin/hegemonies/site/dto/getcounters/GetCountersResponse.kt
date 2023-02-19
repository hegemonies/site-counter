package hegemonies.site.dto.getcounters

import hegemonies.site.dto.CounterDto
import kotlinx.serialization.Serializable

@Serializable
data class GetCountersResponse(
    val counters: List<CounterDto>,
)
