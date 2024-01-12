package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.exceptions.UserNotFoundException;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ReviewDtoMapper implements Function<Review, ReviewDto> {
    private final UserRepository userRepository;
    @Override
    public ReviewDto apply(Review review) {
        User user = userRepository.findById(review.getUserId()).orElseThrow(() -> new UserNotFoundException("Reviewer does not exist"));
        return new ReviewDto(
                review.getId(),
                review.getGame().getId(),
                review.getUserId(),
                review.getSteamId(),
                user.getNickname(),
                review.getTitle(),
                review.getText(),
                review.getRating()
        );
    }
}
