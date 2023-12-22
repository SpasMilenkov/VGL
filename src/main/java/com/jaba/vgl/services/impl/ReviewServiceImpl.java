package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.exceptions.ReviewNotFoundException;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.dto.mapper.ReviewDtoMapper;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.ReviewRepository;
import com.jaba.vgl.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ReviewDtoMapper reviewDtoMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(
                        String.format("Review with id %d not found.",
                                id
                        )
                ));
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity();

        reviewRepository.save(review);
    }

    @Override
    public int deleteReview(Long id) {
        return reviewRepository.deleteReviewById(id);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity();

        reviewRepository.updateReview(review.getId(), review.getGameId(), review.getTitle(), review.getText(), review.getRating());
    }

    @Override
    public void truncateTable() {
        reviewRepository.truncate();
    }
}
