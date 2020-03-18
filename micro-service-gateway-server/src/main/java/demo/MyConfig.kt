package demo

import org.springframework.boot.context.properties.*
import org.springframework.stereotype.*

@Component
@ConfigurationProperties("my-config")
data class MyConfig(
  var myName: String? = null
)
