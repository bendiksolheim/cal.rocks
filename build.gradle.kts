import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

repositories {
	mavenCentral()
	jcenter()
	maven("https://jitpack.io")
}

group = "rocks.cal"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

val arrow_version = "0.8.1"
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.11")

	implementation("io.arrow-kt:arrow-core:$arrow_version")
	implementation("io.arrow-kt:arrow-syntax:$arrow_version")
	implementation("io.arrow-kt:arrow-typeclasses:$arrow_version")
	implementation("io.arrow-kt:arrow-data:$arrow_version")
	implementation("io.arrow-kt:arrow-instances-core:$arrow_version")
	implementation("io.arrow-kt:arrow-instances-data:$arrow_version")
	kapt("io.arrow-kt:arrow-annotations-processor:$arrow_version")
	implementation("io.arrow-kt:arrow-optics:$arrow_version")

	runtime("org.postgresql:postgresql")
}
