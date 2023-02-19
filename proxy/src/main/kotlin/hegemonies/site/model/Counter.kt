package hegemonies.site.model

import org.jetbrains.exposed.sql.Table

object Counter : Table() {
    val id = long("id").autoIncrement()
    val clientAddress = text("client_address")
    val uri = text("uri")
    val headers = text("headers")

    override val primaryKey = PrimaryKey(id)
}
