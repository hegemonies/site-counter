package site.hegemonies.collector.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import site.hegemonies.collector.dto.CounterEventDto
import site.hegemonies.collector.repository.IRedisRepository

@Service
class CounterService(
    private val redisRepository: IRedisRepository,
    private val jsonMapper: ObjectMapper
) {

    suspend fun saveCounter(dto: CounterEventDto) {
        with(dto) {
            val headers = jsonMapper.writeValueAsString(headers)
            redisRepository.save(uri, id, clientAddress, headers)
            redisRepository.increment(uri, clientAddress, headers)
        }
    }
}
