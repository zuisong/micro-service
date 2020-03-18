package demo;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.stereotype.*;

/**
 * @author Chen
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private EurekaSecurityConfig eurekaSecurityConfig;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests(it -> {
        it.anyRequest().authenticated();
      })
      .userDetailsService(new InMemoryUserDetailsManager())
      .formLogin(it -> {
        it.permitAll();
      })
      .csrf(it -> {
        it.disable();
      })
      .httpBasic(it -> {

      })
      .userDetailsService(userDetailsService());
  }


  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
      User
        .withUsername(eurekaSecurityConfig.getUsername())
        .password(eurekaSecurityConfig.getPassword())
        .authorities("ALL")
        .build()
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Data
  @Component
  @ConfigurationProperties("eureka.security")
  public static class EurekaSecurityConfig {
    private String username;
    private String password;

  }
}
