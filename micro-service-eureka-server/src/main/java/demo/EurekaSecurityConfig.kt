package demo

import org.springframework.boot.context.properties.*
import org.springframework.stereotype.*

@Component
@ConfigurationProperties("eureka.security")
data class EurekaSecurityConfig(
  var username: String? = null,
  var password: String? = null
)
