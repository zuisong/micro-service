package com.jte.cloud.platform.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.lang.invoke.MethodHandles;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2ServerApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
    }
}
