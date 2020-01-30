package demo;

import org.springframework.boot.*;
import org.springframework.cloud.client.*;
import org.springframework.cloud.config.server.*;

@EnableConfigServer
@SpringCloudApplication
public class ConfigServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServerApplication.class, args);
  }
}
