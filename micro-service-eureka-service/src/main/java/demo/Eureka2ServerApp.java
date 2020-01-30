package demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.server.*;

import java.lang.invoke.*;

/**
 * Hello world!
 *
 * @author Chen
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2ServerApp {
  public static void main(String[] args) {
    SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
  }
}
