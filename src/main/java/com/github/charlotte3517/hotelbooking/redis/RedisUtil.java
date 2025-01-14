package com.github.charlotte3517.hotelbooking.redis;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Component
public class RedisUtil {
    private RedisTemplate<String, Object> redisTemplate = null;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        if (redisTemplate == null) {
            System.out.println("RedisTemplate is null!");
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            return clazz.cast(redisTemplate.opsForValue().get(key));
        } catch (Exception e) {
            LoggerFactory.getLogger(RedisUtil.class).error("Failed to get key {} from Redis: {}", key, e.getMessage());
            return null;
        }
    }

    public void set(String key, Object value, Duration duration) {
        try {
            redisTemplate.opsForValue().set(key, value, duration);
        } catch (Exception e) {
            LoggerFactory.getLogger(RedisUtil.class).error("Failed to set key {} in Redis: {}", key, e.getMessage());
        }
    }

    public void delete(Set<String> keys) {
        try {
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(RedisUtil.class).error("Failed to delete keys {} from Redis: {}", keys, e.getMessage());
        }
    }
}
