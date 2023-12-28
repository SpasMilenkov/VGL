package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.models.entities.User;
import feign.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepositoryCustom {

    List<Review> getUserReviews(@Param("userId") Long userId);

    Set<Game> getUserGames(@Param("userId") Long userId);

    void truncate();
}
