package com.github.charlotte3517.hotelbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfigLogger implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfigLogger.class);

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Configured JDBC URL: " + jdbcUrl);
        logger.info("Configured JDBC driver-class-name: " + driverClassName);
    }
}
