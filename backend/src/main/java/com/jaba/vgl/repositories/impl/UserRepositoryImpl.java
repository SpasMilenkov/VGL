package com.jaba.vgl.repositories.impl;


import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.repositories.custom.UserRepositoryCustom;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepositoryImpl extends UserRepository, UserRepositoryCustom {

    @Override
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> getUserReviews(@Param("userId") Long userId);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE Users RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
