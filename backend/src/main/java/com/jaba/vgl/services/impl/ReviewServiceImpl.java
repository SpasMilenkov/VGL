package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.ReviewNotFoundException;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.dto.mapper.ReviewDtoMapper;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.impl.ReviewRepositoryImpl;
import com.jaba.vgl.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepositoryImpl reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ReviewServiceImpl(ReviewRepositoryImpl reviewRepository,
                             ReviewDtoMapper reviewDtoMapper,
                             JdbcTemplate jdbcTemplate) {
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ReviewDto getReview(Long id) {
        return reviewRepository
                .findById(id)
                .map(reviewDtoMapper)
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

        String sqlStatement = "ALTER SEQUENCE review_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
