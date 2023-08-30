package com.project2morrow.lapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {


        //configuration

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/home").authenticated().requestMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated();
        return http.build();
    }
}
