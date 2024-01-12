package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.models.entities.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findWishByGameId(Long gameId);
    void deleteByUserAndGame(User user, Game game);
}
