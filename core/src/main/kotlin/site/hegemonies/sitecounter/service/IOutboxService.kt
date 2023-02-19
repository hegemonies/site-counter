package site.hegemonies.sitecounter.service

import site.hegemonies.sitecounter.dto.OutboxEventDto

interface IOutboxService {
    suspend fun sendEvent(outboxEventDto: OutboxEventDto)
}
