package cn.mmooo

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*

@SpringBootApplication
class DefaultAuthorizationServerApplication

fun main(args: Array<String>) {
    runApplication<DefaultAuthorizationServerApplication>(*args)
}

