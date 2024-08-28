package com.github.charlotte3517.hotelbooking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfigLogger implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Configured JDBC URL: " + jdbcUrl);
        System.out.println("Configured JDBC driver-class-name: " + driverClassName);
    }
}
