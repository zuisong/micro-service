package demo

import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.gateway.route.*
import kotlin.streams.*

@SpringBootApplication
class GatewayApplication : CommandLineRunner {
  @Autowired
  private lateinit var routeDefinitionRepository: RouteDefinitionRepository

  @Autowired
  private lateinit var config: MyConfig

  override fun run(vararg args: String?) {

    println(config.myName)
    val list =
      routeDefinitionRepository
        .routeDefinitions
        .toStream()
        .toList()

    println(list)

  }

}

fun main(args: Array<String>) {
  SpringApplication.run(GatewayApplication::class.java, *args)
}
