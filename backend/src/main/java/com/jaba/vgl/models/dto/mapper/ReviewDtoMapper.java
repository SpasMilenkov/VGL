package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.entities.Review;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReviewDtoMapper implements Function<Review, ReviewDto> {
    @Override
    public ReviewDto apply(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getGameId(),
                review.getUserId(),
                review.getTitle(),
                review.getText(),
                review.getRating()
        );
    }
}
