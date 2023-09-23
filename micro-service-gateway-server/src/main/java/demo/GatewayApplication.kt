package demo

import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.gateway.route.*

@SpringBootApplication
class GatewayApplication : CommandLineRunner {
  @Autowired
  private lateinit var routeDefinitionRepository: RouteDefinitionRepository

  @Autowired
  private lateinit var config: MyConfig

  override fun run(vararg args: String?) {

  }
}

fun main(args: Array<String>) {
  runApplication<GatewayApplication>(*args)
}
