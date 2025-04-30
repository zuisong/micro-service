package demo

import com.nimbusds.jose.jwk.source.*
import com.nimbusds.jose.proc.*
import org.springframework.context.annotation.*
import org.springframework.security.config.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.*
import javax.crypto.*
import javax.crypto.spec.*


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .authorizeHttpRequests {
                it.requestMatchers("/login/**").hasRole("role-name")
                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }

        return http.build()
    }


    var secret: String = "s/4KMb61LOrMYYAn4rfaQYSgr+le5SMrsMzKw8G6bXc="

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val key: SecretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val immutableSecret: JWKSource<SecurityContext> = ImmutableSecret(key)
        return NimbusJwtEncoder(immutableSecret)
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val originalKey: SecretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build()
        return jwtDecoder
    }


}
