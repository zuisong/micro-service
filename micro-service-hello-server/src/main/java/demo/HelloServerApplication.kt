package demo

import feign.Logger
import jakarta.validation.Validator
import org.hibernate.validator.HibernateValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.*
import org.springframework.cloud.openfeign.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.security.oauth2.server.resource.web.*
import org.springframework.validation.beanvalidation.*
import org.springframework.web.bind.annotation.RestController


fun main(args: Array<String>) {
  runApplication<HelloServerApplication>(*args)
}


/**
 * @author Chen
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
class HelloServerApplication {
  @Autowired
  private lateinit var client: DiscoveryClient

  @Bean
  fun feignLoggerLevel(): Logger.Level {
    return Logger.Level.FULL
  }

  @Bean
  fun methodValidationPostProcessor(validator: Validator): MethodValidationPostProcessor? {
    return MethodValidationPostProcessor().apply {
      setValidator(validator)
    }
  }

  @Bean
  fun validatorFactoryBean(): LocalValidatorFactoryBean = LocalValidatorFactoryBean().apply {
    setProviderClass(HibernateValidator::class.java)
  }

  @Bean
  fun applicationRunner() = ApplicationRunner {
    client.services.forEach {
      println(it)
    }
  }

  @Bean
  fun bearerTokenResolver(): BearerTokenResolver {
    val bearerTokenResolver = DefaultBearerTokenResolver()
    bearerTokenResolver.setBearerTokenHeaderName(HttpHeaders.PROXY_AUTHORIZATION)
    return bearerTokenResolver
  }

}


