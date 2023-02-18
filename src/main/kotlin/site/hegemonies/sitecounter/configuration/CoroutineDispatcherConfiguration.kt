package site.hegemonies.sitecounter.configuration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.coroutines.CoroutineContext

@Configuration
class CoroutineDispatcherConfiguration {

    @Bean
    fun coroutineScope(): CoroutineScope =
        object : CoroutineScope {
            override val coroutineContext: CoroutineContext = Dispatchers.IO.limitedParallelism(10)
        }
}
