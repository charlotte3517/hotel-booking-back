package com.github.charlotte3517.hotelbooking.task;

import com.github.charlotte3517.hotelbooking.dao.GoogleReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class DeleteOldGoogleReviewsTask {

    @Value("${google.places.daysSinceLastReviewQuery}")
    private Integer numberOfDays;

    @Autowired
    private GoogleReviewDao googleReviewDao;

    @Scheduled(cron = "0 0 2 * * MON")
    public void deleteOldGoogleReviews() {
        googleReviewDao.deleteGoogleReviewBeforeLastNDays(numberOfDays);
    }
}
