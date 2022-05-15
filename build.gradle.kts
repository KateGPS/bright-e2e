plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.1.4")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:4.1.4")
    testRuntimeOnly("org.testng:testng:7.5")
}

tasks.getByName<Test>("test") {
    useTestNG()
}