package site.hegemonies.collector.repository

import io.github.crackthecodeabhi.kreds.connection.KredsClient
import org.springframework.stereotype.Service
import site.hegemonies.collector.constant.RedisConstants

@Service
class RedisRepository(
    private val kredsClient: KredsClient
) : IRedisRepository {

    override suspend fun save(path: String, id: Long, clientAddress: String, headers: String) {
        kredsClient.use { client ->
            client.hset(createKey(path), "$clientAddress:$id" to headers)
        }
    }

    override suspend fun increment(path: String, clientAddress: String, headers: String) {
        kredsClient.use { client ->
            client.hincrBy(createKey(path), clientAddress, 1L)
            client.hincrBy(createKey(path), path, 1L)
        }
    }

    override suspend fun get(key: String) {
        TODO("Not yet implemented")
    }

    private fun createKey(path: String): String =
        "${RedisConstants.KEY_PREFIX}:$path"
}
