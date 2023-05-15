plugins {
    kotlin("jvm") version "1.8.0"
    id("com.google.devtools.ksp") version "1.8.0-1.0.8"
}

group = "me.ibado"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val arrowVersion = "1.1.5"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")

    testImplementation(kotlin("test"))
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}