package hegemonies.site.plugins

import hegemonies.site.configuration.globalCoroutineScope
import hegemonies.site.constant.Constants.DEFAULT_GET_COUNTERS_LIMIT
import hegemonies.site.dto.CounterDto
import hegemonies.site.dto.HttpRequestError
import hegemonies.site.dto.getcounters.GetCountersRequest
import hegemonies.site.dto.getcounters.GetCountersResponse
import hegemonies.site.dto.incrementcounter.IncrementCountRequest
import hegemonies.site.dto.incrementcounter.IncrementCounterResponse
import hegemonies.site.service.CounterService
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.Database

fun Application.configureRouting(database: Database) {

    val counterService = CounterService(database)
    val appToken = environment.config.property("app.token").getString()

    routing {
        post("/api/v1/counter/increment") {
            val request = call.receive<IncrementCountRequest>()
            call.application.environment.log.info("request token = ${request.token}")

            if (request.token == appToken) {
                val response = IncrementCounterResponse(true)
                call.respond(response)

                globalCoroutineScope.launch {
                    val clientAddress = call.request.origin.remoteAddress
                    val headers = call.request.headers.flattenEntries().toMap()

                    counterService.save(clientAddress, request.url, headers)
                }
            } else {
                val response = IncrementCounterResponse(false)
                call.respond(response)
            }
        }

        post("/api/v1/counter/get") {
            val request = call.receive<GetCountersRequest>()
            val response: Any = if (request.token == appToken) {
                val counters = counterService.findAll(request.lastId, request.limit ?: DEFAULT_GET_COUNTERS_LIMIT)
                GetCountersResponse(counters)
            } else {
                HttpRequestError("token is not valid")
            }
            call.respond(response)
        }
    }
}
