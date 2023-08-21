package com.sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//exclude = SecurityAutoConfiguration.class

@SpringBootApplication()
public class SignUpSignInModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignUpSignInModuleApplication.class, args);
    }

}
