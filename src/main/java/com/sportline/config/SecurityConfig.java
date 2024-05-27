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

    public static final String SPORTLINE_ADDR = "/sportline";

    private final UserDetailsWrapperService userDetailsWrapperService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                SPORTLINE_ADDR + "/registration",
                                SPORTLINE_ADDR + "/login",

                                SPORTLINE_ADDR + "/brands",
                                SPORTLINE_ADDR + "/blog",
                                SPORTLINE_ADDR + "/products",
                                SPORTLINE_ADDR,
                                "/css/**",
                                "/img/**"
                        ).permitAll()
                        .anyRequest()
                        .hasAnyRole("USER", "ADMIN")
                )
                .userDetailsService(userDetailsWrapperService)
                .formLogin(form -> form
                        .loginPage(SPORTLINE_ADDR + "/login")
                        .loginProcessingUrl("/sportline/process_login")
                        .defaultSuccessUrl(SPORTLINE_ADDR, true)
                        .failureUrl(SPORTLINE_ADDR + "/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/sportline/logout")
                        .logoutSuccessUrl(SPORTLINE_ADDR + "/login")
                )
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}