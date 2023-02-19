package site.hegemonies.sitecounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableR2dbcRepositories
@ConfigurationPropertiesScan
@EnableScheduling
class SiteCounterApplication

fun main(args: Array<String>) {
    runApplication<SiteCounterApplication>(*args)
}
