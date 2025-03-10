package com.example.mylife.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

/**
 * 访问接口不在调用security
 * @author Liyh
 * @date 2020/12/22
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user")
//                        .password(encoder.encode("password"))
//                        .roles("USER")
//                        .build()
//        );
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
