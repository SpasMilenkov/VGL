package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    ReviewDto getReview(Long id);

    Optional<Long> getReviewId(String title);

    void saveReview(ReviewDto reviewDto);

    void saveReviews(List<ReviewDto> reviewDtos);

    int deleteReview(Long id);

    void updateReview(ReviewDto reviewDto);

    void truncateTable();
}
