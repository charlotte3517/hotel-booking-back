package com.github.charlotte3517.hotelbooking;

import com.github.charlotte3517.hotelbooking.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisConnectionTester implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionTester.class);
    private final RedisUtil redisUtil;

    public RedisConnectionTester(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void run(String... args) {
        String testKey = "connectionTestKey";
        String testValue = "connectionTestValue";

        try {
            redisUtil.set(testKey, testValue, null);
            String valueFromRedis = redisUtil.get(testKey, String.class);

            if (testValue.equals(valueFromRedis)) {
                logger.info("Redis connection test successful. Key: {}, Value: {}", testKey, valueFromRedis);
            } else {
                logger.warn("Redis connection test failed. Retrieved value is null or incorrect.");
            }

            redisUtil.delete(Set.of(testKey));
        } catch (Exception e) {
            logger.error("Redis connection test failed with error: {}", e.getMessage(), e);
        }
    }
}
