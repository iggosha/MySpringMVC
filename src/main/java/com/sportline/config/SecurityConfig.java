package com.sportline.config;

import com.sportline.service.UserDetailsWrapperService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    public static final String SPORTLINE_LOGIN = "/sportline/login";
    private final UserDetailsWrapperService userDetailsWrapperService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/sportline/registration",
                                SPORTLINE_LOGIN,
                                "/css/**",
                                "/img/**"
                        ).permitAll()

                        .anyRequest()
                        .hasAnyRole("USER", "ADMIN")
                )
                .userDetailsService(userDetailsWrapperService)
                .formLogin(form -> form
                        .loginPage(SPORTLINE_LOGIN)
                        .loginProcessingUrl("/sportline/process_login")
                        .defaultSuccessUrl("/sportline", true)
                        .failureUrl("/sportline/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/sportline/logout")
                        .logoutSuccessUrl(SPORTLINE_LOGIN)
                )
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}