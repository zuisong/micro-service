package demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer


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
  runApplication<ConfigServerApplication>(*args)
}
