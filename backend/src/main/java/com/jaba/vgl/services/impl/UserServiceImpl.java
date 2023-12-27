package com.jaba.vgl.services.impl;


import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.dto.mapper.GameWithCompanyDtoMapper;
import com.jaba.vgl.models.dto.mapper.ReviewDtoMapper;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.impl.ReviewRepositoryImpl;
import com.jaba.vgl.repositories.impl.UserRepositoryImpl;
import com.jaba.vgl.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jaba.vgl.models.entities.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final ReviewRepositoryImpl reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final GameWithCompanyDtoMapper gameWithCompanyDtoMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            }
        };
    }

    public User loadUserByRefreshToken(String refreshToken) {
        Optional<User> optionalUser = userRepository.findByRefreshToken(refreshToken);
        return optionalUser.orElse(null);
    }

    @Override
    public List<ReviewDto> getUserReviews(Long userId) {
        return userRepository.getUserReviews(userId)
                .stream()
                .map(reviewDtoMapper)
                .toList();
    }

    @Override
    public List<GameWithCompanyDto> getUserGames(Long userId) {
        return userRepository.getUserGames(userId)
                .stream()
                .map(gameWithCompanyDtoMapper)
                .toList();
    }

    @Override
    public void updateUserReview(ReviewDto reviewDto) {
        Review review = reviewDto.toEntity();

        reviewRepository.updateReview(review.getId(), review.getGameId(), review.getTitle(), review.getText(), review.getRating());
    }

    @Override
    public void deleteUserReview(Long reviewId) {
        reviewRepository.deleteReviewById(reviewId);
    }

    @Override
    public void truncateTable() {
        userRepository.truncate();

        String sqlStatement = "ALTER SEQUENCE vgl_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
