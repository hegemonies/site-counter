package site.hegemonies.sitecounter.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import site.hegemonies.sitecounter.model.ProxyCollectLastId

interface ProxyCollectLastIdRepository : CoroutineCrudRepository<ProxyCollectLastId, Long>
