package demo

import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.core.userdetails.*
import org.springframework.security.crypto.password.*
import org.springframework.security.provisioning.*

/**
 * @author Chen
 */
@EnableWebSecurity
class SecurityConfig(
  @Autowired
  private val eurekaSecurityConfig: EurekaSecurityConfig

) : WebSecurityConfigurerAdapter() {


  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests {
        it.anyRequest().authenticated()
      }
      .formLogin {
        it.permitAll()
      }
      .csrf {
        it.disable()
      }
      .httpBasic { }
      .userDetailsService(userDetailsService())
  }

  @Bean
  override fun userDetailsService(): UserDetailsService {
    return InMemoryUserDetailsManager(
      User
        .withUsername(eurekaSecurityConfig.username!!)
        .password(eurekaSecurityConfig.password!!)
        .authorities("ALL")
        .build()
    )
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return NoOpPasswordEncoder.getInstance()
  }

}

