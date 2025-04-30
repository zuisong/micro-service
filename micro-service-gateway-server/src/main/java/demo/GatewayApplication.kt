package demo

import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.gateway.route.*
import java.lang.System.Logger.*

@SpringBootApplication
class GatewayApplication : CommandLineRunner {
  @Autowired
  private lateinit var routeDefinitionRepository: RouteDefinitionRepository

  @Autowired
  private lateinit var config: MyConfig

  override fun run(vararg args: String) {
    val logger = System.getLogger (GatewayApplication::class.java.name)
    logger.log(Level.INFO) { "GatewayApplication" + args.joinToString() }


    logger.log(Level.INFO) { "routeDefinitions -> " + routeDefinitionRepository.routeDefinitions.blockLast()?.toString() }
  }
}

fun main(args: Array<String>) {
  runApplication<GatewayApplication>(*args)
}
