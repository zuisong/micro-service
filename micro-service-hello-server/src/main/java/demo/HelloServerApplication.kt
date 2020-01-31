package demo;

import feign.*;
import org.springframework.beans.factory.annotation.*;
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
@RestController
@EnableFeignClients
public class HelloServerApplication {
  @Autowired
  private DiscoveryClient client;

  public static void main(String[] args) {
    SpringApplication.run(HelloServerApplication.class, args);
  }


  @Bean
  public Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.FULL;
  }

}
