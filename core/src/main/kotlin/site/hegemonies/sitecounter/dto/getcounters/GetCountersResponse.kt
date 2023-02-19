package hegemonies.site.dto.getcounters

import kotlinx.serialization.Serializable
import site.hegemonies.sitecounter.dto.CounterDto

@Serializable
data class GetCountersResponse(
    val counters: List<CounterDto>,
)
