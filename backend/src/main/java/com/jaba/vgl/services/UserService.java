package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<ReviewDto> getUserReviews(Long userId);

    List<GameWithCompanyDto> getUserGames(Long userId);
    UserDto updateSteamId(String email, String steamId);

    void updateUserReview(ReviewDto reviewDto);

    void deleteUserReview(Long reviewId);

    void truncateTable();
}
