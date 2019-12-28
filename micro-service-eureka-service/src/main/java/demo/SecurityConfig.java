package demo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

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
