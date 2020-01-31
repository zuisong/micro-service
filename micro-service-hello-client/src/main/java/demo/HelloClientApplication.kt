package demo

import demo.bean.*
import feign.*
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
@EnableFeignClients
class HelloClientApplication {
  @FeignClient("HELLO-SERVER")
  internal interface HelloClient {
    @ResponseBody
    @RequestMapping(value = ["/get"], method = [RequestMethod.GET])
    fun hello1(@RequestParam("name") name: String?): User?

    @ResponseBody
    @RequestMapping(value = ["/post"], method = [RequestMethod.POST])
    fun hello2(@RequestParam("name") name: String?): User?
  }

  @Bean
  fun feignLoggerLevel(): Logger.Level {
    return Logger.Level.FULL
  }

}

fun main(args: Array<String>) {
  SpringApplication.run(HelloClientApplication::class.java, *args)
}
