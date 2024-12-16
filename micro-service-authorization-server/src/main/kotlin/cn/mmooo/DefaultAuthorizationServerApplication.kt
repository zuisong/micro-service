package cn.mmooo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DefaultAuthorizationServerApplication

fun main(args: Array<String>) {
    runApplication<DefaultAuthorizationServerApplication>(*args)
}

