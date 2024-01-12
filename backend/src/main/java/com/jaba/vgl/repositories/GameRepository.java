package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>{
    Optional<Game> findGameById(Long id);

    List<Game> findByIdIn(List<Long> ids);
    int deleteGameById(Long id);

    Optional<Game> findBySteamId(Long gameId);
}
