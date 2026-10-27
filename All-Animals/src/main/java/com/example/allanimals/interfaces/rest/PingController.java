package com.example.allanimals.interfaces.rest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final JdbcTemplate jdbc;

    public PingController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/ping")
    public String ping() {
        jdbc.queryForObject("SELECT 1", Integer.class);
        return "ok";
    }
}