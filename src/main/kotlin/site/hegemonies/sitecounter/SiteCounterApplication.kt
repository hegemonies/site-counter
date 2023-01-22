package site.hegemonies.sitecounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SiteCounterApplication

fun main(args: Array<String>) {
	runApplication<SiteCounterApplication>(*args)
}
