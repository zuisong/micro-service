package demo

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


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
