package com.jaba.vgl.repositories.impl;


import com.jaba.vgl.models.Role;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.repositories.custom.UserRepositoryCustom;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryImpl extends UserRepository, UserRepositoryCustom {

    @Override
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> getUserReviews(@Param("userId") Long userId);

    @Override
    int deleteUserReview(Long reviewId);

    @Override
    void updateUserReview(Review review);
}
