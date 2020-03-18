package demo

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.config.server.*


/**
 * Hello world!
 *
 * @author Chen
 */
@SpringBootApplication
@EnableConfigServer
class ConfigServerApplication : CommandLineRunner {
  override fun run(vararg args: String?) {
    println("---------- application started")
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(ConfigServerApplication::class.java, *args)
}
