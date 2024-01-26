plugins {
    kotlin("jvm") version "1.9.21"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("me.clip:placeholderapi:2.11.5")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

tasks.jar {
    dependsOn(tasks.clean)
}