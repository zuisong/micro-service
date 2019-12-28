package demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class GatewayApplication {
    @Autowired
    RouteDefinitionRepository routeDefinitionRepository;

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
