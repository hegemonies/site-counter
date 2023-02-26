package hegemonies.site.model

import org.jetbrains.exposed.sql.Table
import kotlin.concurrent.timerTask

object Counter : Table() {
    val id = long("id").autoIncrement()
    val clientAddress = text("client_address")
    val uri = text("uri")
    val headers = text("headers")
    val createdAt = long("created_at")

    override val primaryKey = PrimaryKey(id)
}
