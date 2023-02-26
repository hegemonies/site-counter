package site.hegemonies.collector.repository

interface IRedisRepository {
    suspend fun save(path: String, id: Long, clientAddress: String, headers: String)
    suspend fun increment(path: String, clientAddress: String, headers: String)
    suspend fun get(key: String)
}
