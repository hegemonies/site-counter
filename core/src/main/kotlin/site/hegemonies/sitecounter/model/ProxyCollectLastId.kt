package site.hegemonies.sitecounter.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import site.hegemonies.sitecounter.consts.TableNames

@Table(name = TableNames.PROXY_COLLECT_LAST_ID)
data class ProxyCollectLastId(
    @Id
    val id: Long? = null,
    val lastId: Long,
)
