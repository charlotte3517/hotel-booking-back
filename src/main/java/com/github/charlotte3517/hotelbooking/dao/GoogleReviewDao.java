package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoogleReviewDao {

    List<GoogleReview> getAllGoogleReviews();

    GoogleReview getGoogleReviewById(@Param("id") Integer id);

    GoogleReview getGoogleReviewByPlaceId(@Param("placeId") String placeId);

    void insertGoogleReview(@Param("googleReview") GoogleReview googleReview);

    void updateGoogleReview(@Param("googleReview") GoogleReview googleReview);

    void deleteGoogleReview(@Param("id") Integer id);

    void deleteGoogleReviewBeforeLastNDays(@Param("numberOfDays") Integer numberOfDays);

    void deleteGoogleReviewByPlaceIdBeforeLastNDays(@Param("placeId") String placeId,
                                                    @Param("numberOfDays") Integer numberOfDays);

    Integer getReviewCountByHotelId(@Param("hotelId") Integer hotelId);

    Integer getGoogleReviewCountByPlaceIdInLastNDays(@Param("placeId") String placeId,
                                                     @Param("numberOfDays") Integer numberOfDays);

    List<GoogleReview> getGoogleReviewByPlaceIdInLastNDays(@Param("placeId") String placeId,
                                                           @Param("numberOfDays") Integer numberOfDays);
}