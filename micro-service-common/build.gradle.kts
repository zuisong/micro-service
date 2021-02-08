plugins {
  kotlin("jvm")
  groovy
  `java-library`
}

dependencies {
  implementation("org.apache.commons:commons-lang3")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.spockframework:spock-core")
}

description = "micro-service-common"
