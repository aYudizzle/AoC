plugins {
    kotlin("jvm")
}

group = "dev.ayupi.core"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("io.ktor:ktor-client-core:3.0.1")
//    implementation("io.ktor:ktor-client-cio:3.0.1")
    implementation(project.libs.ktor.client.core)
    implementation(project.libs.ktor.client.cio)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}