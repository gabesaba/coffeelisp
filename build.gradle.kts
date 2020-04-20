plugins {
    kotlin("jvm") version "1.3.70"
    application
}

repositories {
    jcenter()
}


group = "org.gabe.coffee"

version = "1.0"

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "coffeelisp.MainKt"
}
