package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.Review;

public record ReviewDto(
        String title,
        String text,
        Float rating
) {
    public Review toEntity() {
        Review review = new Review();
        ReviewDto dto = this;

        review.setTitle(dto.title);
        review.setText(dto.text);
        review.setRating(dto.rating);

        return review;
    }
}
