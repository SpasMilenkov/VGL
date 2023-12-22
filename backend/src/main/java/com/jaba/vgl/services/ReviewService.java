package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.entities.Review;

public interface ReviewService {

    Review getReview(Long id);

    void saveReview(ReviewDto reviewDto);

    int deleteReview(Long id);

    void updateReview(ReviewDto reviewDto);

    void truncateTable();
}
