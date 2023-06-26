package site.hegemonies.sitecounter.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import site.hegemonies.sitecounter.consts.TableNames
import java.time.Instant

@Table(name = TableNames.OUTBOX)
data class OutboxMessage(
    @Id
    val id: Long? = null,
    val createdAt: Instant,
    val topic: String,
    val key: String,
    val message: String,
)
