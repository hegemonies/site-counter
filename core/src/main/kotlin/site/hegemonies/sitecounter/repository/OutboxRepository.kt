package site.hegemonies.sitecounter.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import site.hegemonies.sitecounter.model.OutboxMessage

interface OutboxRepository : CoroutineCrudRepository<OutboxMessage, Long>
