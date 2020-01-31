package demo;

import feign.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chen
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class HelloClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloClientApplication.class, args);
  }


  @FeignClient("HELLO-SERVER")
  interface HelloClient {

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    User hello1(@RequestParam("name") String name);

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    User hello2(@RequestParam("name") String name);

  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.FULL;
  }

}
