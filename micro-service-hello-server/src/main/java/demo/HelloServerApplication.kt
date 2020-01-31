package demo

import feign.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.client.discovery.*
import org.springframework.cloud.openfeign.*
import org.springframework.context.annotation.*
import org.springframework.web.bind.annotation.*

/**
 * @author Chen
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
class HelloServerApplication {
  @Autowired
  private lateinit var client: DiscoveryClient

  @Bean
  fun feignLoggerLevel(): Logger.Level {
    return Logger.Level.FULL
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(HelloServerApplication::class.java, *args)
}
