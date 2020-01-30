package demo;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.gateway.route.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;

@SpringBootApplication
public class GatewayApplication {
  @Autowired
  private RouteDefinitionRepository routeDefinitionRepository;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }


  @Bean
  @Order
  public InitializingBean init(MyConfig config) {

    return () -> {
      System.out.println(config.getMyName());
      routeDefinitionRepository.getRouteDefinitions()
        .subscribe(it -> {
          System.out.println(it);
        });
    };
  }
}
