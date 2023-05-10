package demo

import demo.bean.*
import org.springframework.cloud.openfeign.*
import org.springframework.web.bind.annotation.*

@FeignClient("hello-server")
interface HelloServerClient {
  @ResponseBody
  @RequestMapping(value = ["/get"], method = [RequestMethod.GET])
  fun hello1(@RequestParam("name") name: String?): User?

  @ResponseBody
  @RequestMapping(value = ["/post"], method = [RequestMethod.POST])
  fun hello2(@RequestParam("name") name: String ,@RequestParam age: Int ): User?
}
