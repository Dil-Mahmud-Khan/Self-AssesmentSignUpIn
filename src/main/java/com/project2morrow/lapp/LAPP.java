package com.project2morrow.lapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LAPP {

    public static void main(String[] args) {
        SpringApplication.run(LAPP.class, args);
    }

}
