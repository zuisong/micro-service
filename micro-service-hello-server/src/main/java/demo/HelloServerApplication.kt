package demo

import demo.bean.*
import feign.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.client.discovery.*
import org.springframework.cloud.openfeign.*
import org.springframework.context.annotation.*
import org.springframework.web.bind.annotation.*


fun main(args: Array<String>) {
  SpringApplication.run(HelloServerApplication::class.java, *args)
}


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

  @Bean
  fun applicationRunner() = ApplicationRunner {
    client.services.forEach {
      println(it)
    }
  }

}


@RestController
class DemoHandler {
  @GetMapping("/get")
  fun hello1(@RequestParam("name") name: String?): User {
    //ServiceInstance localInstance = client.getLocalServiceInstance();
    // return "Hello " + name + ": " + localInstance.getServiceId() + ":" +
    // localInstance.getHost() + ":"
    // + localInstance.getPort();
    val user = User(name, "Hello World")
    println(user)
    return user
  }

  @PostMapping("/post")
  fun hello2(@RequestParam("name") name: String?): User {
    //ServiceInstance localInstance = client.getLocalServiceInstance();
    // return "Hello " + name + ": " + localInstance.getServiceId() + ":" +
    // localInstance.getHost() + ":"
    // + localInstance.getPort();
    val user = User(name, "Hello World")
    println(user)
    return user
  }
}

