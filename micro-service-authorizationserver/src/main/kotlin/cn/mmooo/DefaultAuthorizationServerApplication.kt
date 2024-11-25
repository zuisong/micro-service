package cn.mmooo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DefaultAuthorizationServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(DefaultAuthorizationServerApplication::class.java, *args)
}

