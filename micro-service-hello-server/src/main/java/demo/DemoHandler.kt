package demo

import demo.bean.*
import jakarta.validation.*
import jakarta.validation.constraints.*
import org.springframework.validation.annotation.*
import org.springframework.web.bind.annotation.*


@RestController
@Validated
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
  fun hello2(@Valid user: User?, @Max(200) @NotNull @RequestParam age: Int?): User {
    //ServiceInstance localInstance = client.getLocalServiceInstance();
    // return "Hello " + name + ": " + localInstance.getServiceId() + ":" +
    // localInstance.getHost() + ":"
    // + localInstance.getPort();
    val user = User(user!!.name, "Hello World")
    println(user)
    return user
  }

}
