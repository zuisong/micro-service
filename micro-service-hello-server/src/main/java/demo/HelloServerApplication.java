package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class HelloServerApplication {
    @Autowired
    DiscoveryClient client;

    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }


    @GetMapping("/get")
    @ResponseBody
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
    @ResponseBody
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
