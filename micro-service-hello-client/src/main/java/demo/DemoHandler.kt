package demo

import demo.bean.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class DemoHandler {
  @Autowired
  private lateinit var helloServerClient: HelloServerClient

  @RequestMapping("/")
  fun hello(@RequestParam("name") name: String?): User? {
    val user = helloServerClient.hello1(name)
    val user2 = helloServerClient.hello2(name!!, 20)
    println("从HelloServer 获取到了一个用户$user")
    println("从HelloServer 获取到了一个用户$user2")
    return user
  }
}
