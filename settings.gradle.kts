pluginManagement {
  val spring_boot_version: String by settings
  val kotlin_version: String by settings
  plugins {
    id("org.jetbrains.kotlin.jvm") version kotlin_version apply false
    id("org.springframework.boot") version spring_boot_version apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlin_version apply false
  }
}

rootProject.name = "micro-service"
include(":micro-service-gateway-server")
include(":micro-service-hello-client")
include(":micro-service-hello-server")
include(":micro-service-config-server")
include(":micro-service-common")
include(":micro-service-eureka-service")
