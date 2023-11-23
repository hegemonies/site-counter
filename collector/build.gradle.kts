import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
	id("com.google.cloud.tools.jib") version "3.4.0"
	kotlin("plugin.serialization") version "1.9.21"
}

group = "site.hegemonies"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["coroutinesVersion"] = "1.7.3"

dependencyManagement {
	imports {
		mavenBom("org.jetbrains.kotlinx:kotlinx-coroutines-bom:${property("coroutinesVersion")}")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.kafka:spring-kafka")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.89.Final:osx-aarch_64")

	implementation("io.github.crackthecodeabhi:kreds:0.8.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
	from {
		image = "bellsoft/liberica-runtime-container:jre-21-slim-glibc"
	}
	to {
		image = "hegemonies/site-counter:collector-$version"
	}
}
