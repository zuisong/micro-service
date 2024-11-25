package demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests {
            it.requestMatchers("/login/**").hasRole("role-name")
            it.anyRequest().authenticated()
        }.oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }

        return http.build()
    }
}
