package com.github.charlotte3517.hotelbooking.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class RedisCleanupTask {

    private static final Logger logger = LoggerFactory.getLogger(RedisCleanupTask.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanExpiredHotelsByCityCache() {
        cleanRedisKeys("hotelsByCity:*", "Expired hotelsByCity cache");
    }

    @Scheduled(cron = "0 0 0 2 * ?")
    public void cleanExpiredHotels() {
        cleanRedisKeys("hotel:*", "Expired hotel data");
    }

    @Scheduled(cron = "0 0 0 3 * ?")
    public void cleanExpiredGoogleReviews() {
        cleanRedisKeys("googleReviews:*", "Expired Google reviews");
    }

    private void cleanRedisKeys(String keyPattern, String description) {
        try (var cursor = redisTemplate.getConnectionFactory().getConnection()
                .scan(ScanOptions.scanOptions().match(keyPattern).count(1000).build())) {
            while (cursor.hasNext()) {
                redisTemplate.delete(new String(cursor.next()));
            }
            logger.info("{} cleaned from Redis.", description);
        } catch (Exception e) {
            logger.error("Error occurred while cleaning {} from Redis.", description, e);
        }
    }
}
