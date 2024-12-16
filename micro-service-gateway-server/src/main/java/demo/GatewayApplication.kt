package demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteDefinitionRepository
import java.util.logging.*

@SpringBootApplication
class GatewayApplication : CommandLineRunner {
  @Autowired
  private lateinit var routeDefinitionRepository: RouteDefinitionRepository

  @Autowired
  private lateinit var config: MyConfig

  override fun run(vararg args: String?) {
    Logger.getAnonymousLogger().info { "GatewayApplication" + args.joinToString() }
  }
}

fun main(args: Array<String>) {
  runApplication<GatewayApplication>(*args)
}
