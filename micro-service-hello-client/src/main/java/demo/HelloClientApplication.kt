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
  SpringApplication.run(HelloClientApplication::class.java, *args)
}


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class HelloClientApplication {

  @Bean
  fun feignLoggerLevel(): Logger.Level {
    return Logger.Level.FULL
  }

}

@FeignClient("hello-server")
interface HelloServerClient {
  @ResponseBody
  @RequestMapping(value = ["/get"], method = [RequestMethod.GET])
  fun hello1(@RequestParam("name") name: String?): User?

  @ResponseBody
  @RequestMapping(value = ["/post"], method = [RequestMethod.POST])
  fun hello2(@RequestParam("name") name: String?): User?
}


@RestController
class DemoHandler {
  @Autowired
  private lateinit var helloServerClient: HelloServerClient

  @RequestMapping("/")
  fun hello(@RequestParam("name") name: String?): User? {
    val user = helloServerClient.hello1(name)
    val user2 = helloServerClient.hello2(name)
    println("从HelloServer 获取到了一个用户$user")
    println("从HelloServer 获取到了一个用户$user2")
    return user
  }
}

