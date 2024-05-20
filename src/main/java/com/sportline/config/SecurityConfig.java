package com.sportline.config;

import com.sportline.service.UserDetailsWrapperService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsWrapperService userDetailsWrapperService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/sportline/login", "/css/**", "/img/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .userDetailsService(userDetailsWrapperService)
                .formLogin(form -> form
                        .loginPage("/sportline/login")
                        .loginProcessingUrl("/sportline/process_login")
                        .defaultSuccessUrl("/sportline", true)
                        .failureUrl("/sportline/login?error")
                )
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}