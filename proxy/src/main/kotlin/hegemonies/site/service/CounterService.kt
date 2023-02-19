package hegemonies.site.service

import hegemonies.site.dto.CounterDto
import hegemonies.site.model.Counter
import hegemonies.site.utils.dbQuery
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class CounterService(private val database: Database) {

    init {
        transaction(database) { SchemaUtils.create(Counter) }
    }

    suspend fun save(
        clientAddress: String,
        uri: String,
        headers: Map<String, String>,
    ): Long = dbQuery {
        Counter.insert {
            it[Counter.clientAddress] = clientAddress
            it[Counter.uri] = uri
            it[Counter.headers] = Json.encodeToString(headers)
        }[Counter.id]
    }

    suspend fun findAll(lastId: Long, limit: Int): List<CounterDto> = dbQuery {
        Counter.select { Counter.id greater lastId }
            .orderBy(Counter.id to SortOrder.ASC)
            .limit(limit)
            .map(CounterDto::fromResultRaw)
    }
}
