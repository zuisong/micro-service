package demo

import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.security.config.*
import org.springframework.security.config.Customizer.*
import org.springframework.security.config.annotation.authentication.builders.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.*
import org.springframework.security.core.userdetails.*
import org.springframework.security.crypto.password.*
import org.springframework.security.provisioning.*
import org.springframework.security.web.*


/**
 * @author Chen
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val eurekaSecurityConfig: EurekaSecurityConfig
) {

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(
        http: HttpSecurity, userDetailsService: UserDetailsService
    ): SecurityFilterChain? {
        http
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .formLogin {
                it.permitAll()
            }
            .csrf {
                it.disable()
            }
            .httpBasic {}
            .userDetailsService(userDetailsService)
        return http.build()
    }


    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
            User.withUsername(eurekaSecurityConfig.username!!).password(eurekaSecurityConfig.password!!)
                .authorities("ALL").build()
        )
    }

    @Suppress("DEPRECATION")
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

}

