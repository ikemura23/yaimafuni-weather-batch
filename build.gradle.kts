import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "com.yaimafuni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val ktorVersion = "2.2.1"
dependencies {
    implementation("io.ktor:ktor-client-core: $ktorVersion")
    implementation("io.ktor:ktor-client-okhttp: $ktorVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}