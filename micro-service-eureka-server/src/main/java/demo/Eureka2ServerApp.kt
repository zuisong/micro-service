package demo

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.netflix.eureka.server.*

/**
 * Hello world!
 *
 * @author Chen
 */
@SpringBootApplication
@EnableEurekaServer
class Eureka2ServerApp : CommandLineRunner {
  override fun run(vararg args: String?) {
    println("---------- application started")
  }
}

fun main(args: Array<String>) {


  SpringApplication.run(Eureka2ServerApp::class.java, *args)
}
