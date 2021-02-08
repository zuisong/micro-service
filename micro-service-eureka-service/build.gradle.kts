plugins {
  id("org.springframework.boot")
  kotlin("jvm")
  kotlin("plugin.spring")
  groovy
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
  implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.spockframework:spock-core")
}

description = "micro-service-eureka-service"
