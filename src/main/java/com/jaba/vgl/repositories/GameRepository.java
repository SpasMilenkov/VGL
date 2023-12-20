package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findGameById(Long id);

    Optional<Game> findGameByName(String name);

    Optional<Game> findGameByNameAndCompany(String name, String company);

    void updateGame(Long id, String name, String description, Float rating, String genre, String company, String studio, Boolean isFavourite, String releaseDate);

    void createGame(Game game);

    int deleteGameById(Long id);

    void truncate();
}
