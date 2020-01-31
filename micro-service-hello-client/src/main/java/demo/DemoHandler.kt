package demo

import demo.HelloClientApplication.*
import demo.bean.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class DemoHandler {
  @Autowired
  private lateinit var client: HelloClient

  @RequestMapping("/")
  fun hello(@RequestParam("name") name: String?): User? {
    val user = client.hello1(name)
    val user2 = client.hello2(name)
    println("从HelloServerh获取到了一个用户$user$user2")
    return user
  }
}
