plugins {
    kotlin("jvm") version "1.4.0"
}

group = "me.theforbiddenai"
version = "1.0.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.github.kittinunf.fuel:fuel:2.2.3")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.jsoup:jsoup:1.13.1")
}