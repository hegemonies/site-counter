package hegemonies.site.configuration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
private fun coroutineScope(): CoroutineScope =
    object : CoroutineScope {
        override val coroutineContext: CoroutineContext =
            Dispatchers.IO.limitedParallelism(10) + SupervisorJob()
    }

val globalCoroutineScope = coroutineScope()
