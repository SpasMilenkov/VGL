package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.services.impl.ReviewServiceImpl;

import java.util.Optional;

public record ReviewDto(
        Long id,
        Long gameId, //TODO: migrate to GameDetailsDto?
        Long userId, //TODO: migrate to UserDto
        String title,
        String text,
        Float rating
) {
    public Review toEntity(ReviewServiceImpl reviewService) {
        Review review = new Review();
        ReviewDto dto = this;

        Optional<Long> id = reviewService.getReviewId(dto.title());
        id.ifPresent(review::setId);

        review.setGameId(dto.gameId);
        review.setUserId(dto.userId);
        review.setTitle(dto.title);
        review.setText(dto.text);
        review.setRating(dto.rating);

        return review;
    }
}
