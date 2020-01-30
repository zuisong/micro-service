package demo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoHandler {

  @Autowired
  private HelloClientApplication.HelloClient client;

  @RequestMapping("/")
  public User hello(@RequestParam("name") String name) {
    User user  = client.hello1(name);
    User user2 = client.hello2(name);
    System.out.println("从HelloServerh获取到了一个用户" + user + user2);
    return user;
  }
}
