package site.hegemonies.collector.repository

import io.github.crackthecodeabhi.kreds.connection.KredsClient
import org.springframework.stereotype.Service

@Service
class RedisRepository(
    private val kredsClient: KredsClient
) : IRedisRepository {

    override suspend fun save(path: String, id: Long, clientAddress: String, headers: String) {
        kredsClient.use { client ->
            client.hset(path, "$clientAddress:$id" to headers)
        }
    }

    override suspend fun increment(path: String, clientAddress: String, headers: String) {
        kredsClient.use { client ->
            client.hincrBy(path, clientAddress, 1L)
        }
    }

    override suspend fun get(key: String) {
        TODO("Not yet implemented")
    }
}
