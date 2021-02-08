plugins {
  val kotlin_version = "1.4.30"
  groovy
  id("org.jetbrains.kotlin.jvm") version kotlin_version apply false
  id("org.jetbrains.kotlin.plugin.spring") version kotlin_version apply false
}

subprojects {
  apply {
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.gradle.groovy")
  }
  repositories {
    mavenLocal()
    maven("https://mirrors.huaweicloud.com/repository/maven/")

    maven("https://repo.maven.apache.org/maven2/")
  }
  val implementation by configurations
  val testImplementation by configurations
  dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2020.0.1"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.4.2"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.30"))
    implementation(platform("org.spockframework:spock-bom:2.0-M4-groovy-3.0"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.spockframework:spock-core")
  }
  tasks.withType<Test> {
    useJUnitPlatform()
  }

  group = "cn.mmooo"
  version = "1.0.2"
}
