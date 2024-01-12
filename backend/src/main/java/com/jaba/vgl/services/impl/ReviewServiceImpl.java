package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.ReviewNotFoundException;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.dto.mapper.ReviewDtoMapper;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.GameRepository;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.repositories.impl.ReviewRepositoryImpl;
import com.jaba.vgl.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepositoryImpl reviewRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ReviewServiceImpl(ReviewRepositoryImpl reviewRepository,
                             GameRepository gameRepository, UserRepository userRepository, ReviewDtoMapper reviewDtoMapper,
                             JdbcTemplate jdbcTemplate) {
        this.reviewRepository = reviewRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
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
    public Optional<Long> getReviewId(String title) {
        return reviewRepository.getReviewId(title);
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity(this);

        review.setGame(gameRepository.findById(reviewDto.gameId()).get());
        review.setUser(userRepository.findById(reviewDto.userId()).get());

        reviewRepository.save(review);
    }

    @Override
    public void saveReviews(List<ReviewDto> reviewDtos) {
        List<Review> reviews = reviewDtos
                .stream()
                .map(dto -> dto.toEntity(this))
                .toList();

        reviewRepository.saveAll(reviews);
    }

    @Override
    public int deleteReview(Long id) {
        return reviewRepository.deleteReviewById(id);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity(this);

        reviewRepository.updateReview(review.getId(), review.getGame().getId(), review.getTitle(), review.getText(), review.getRating());
    }

    @Override
    public void truncateTable() {
        reviewRepository.truncate();

        String sqlStatement = "ALTER SEQUENCE review_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
