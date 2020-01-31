package demo

import demo.bean.*
import org.springframework.web.bind.annotation.*

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
