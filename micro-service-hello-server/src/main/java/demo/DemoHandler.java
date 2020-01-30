package demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoHandler {

  @GetMapping("/get")
  public User hello1(@RequestParam("name") String name) {
    //ServiceInstance localInstance = client.getLocalServiceInstance();
    // return "Hello " + name + ": " + localInstance.getServiceId() + ":" +
    // localInstance.getHost() + ":"
    // + localInstance.getPort();
    User user = new User(name, "Hello World");
    System.out.println(user);
    return user;
  }

  @PostMapping("/post")
  public User hello2(@RequestParam("name") String name) {
    //ServiceInstance localInstance = client.getLocalServiceInstance();
    // return "Hello " + name + ": " + localInstance.getServiceId() + ":" +
    // localInstance.getHost() + ":"
    // + localInstance.getPort();
    User user = new User(name, "Hello World");
    System.out.println(user);
    return user;
  }

}
