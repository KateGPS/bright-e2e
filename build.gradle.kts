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
    testImplementation("org.testng:testng:7.5")
    testImplementation("org.slf4j:slf4j-jdk14:1.7.36")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.1.1")
}

tasks.getByName<Test>("test") {
    useTestNG()
}