package com.example.allanimals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AllAnimalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllAnimalsApplication.class, args);
    }

}