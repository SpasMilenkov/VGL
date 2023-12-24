package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.entities.Review;
import feign.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    List<Review> getUserReviews(@Param("userId") Long userId);
}
