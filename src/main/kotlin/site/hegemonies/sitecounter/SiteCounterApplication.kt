package site.hegemonies.sitecounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@ConfigurationPropertiesScan
class SiteCounterApplication

fun main(args: Array<String>) {
    runApplication<SiteCounterApplication>(*args)
}
