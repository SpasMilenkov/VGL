package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameDetailsRepository extends JpaRepository<GameDetails, Long> {

    Optional<GameDetails> findGameDetailsById(Long id);

    Optional<GameDetails> findGameDetailsByName(String name);

    Optional<GameDetails> findGameDetailsByNameAndCompany(String name, String company);

    void updateGameDetails(Long id, String name, String description, Float rating, String genre, String company, String studio, Boolean isFavourite, String releaseDate);

    void createGameDetails(GameDetails game);

    int deleteGameDetailsById(Long id);

    void truncate();
}
