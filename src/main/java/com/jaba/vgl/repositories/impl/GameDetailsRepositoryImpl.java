package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.repositories.GameDetailsRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(
        rollbackFor = Exception.class,
        propagation = Propagation.REQUIRED
)
public interface GameDetailsRepositoryImpl extends GameDetailsRepository {

    @Override
    @Query("SELECT g FROM GameDetails g WHERE g.id = ?1")
    Optional<GameDetails> findGameDetailsById(Long id);

    @Override
    @Query("SELECT g FROM GameDetails g WHERE g.name = ?1")
    Optional<GameDetails> findGameDetailsByName(String name);

    @Override
    @Query("SELECT g FROM GameDetails g WHERE g.name = ?1 AND g.company = ?2")
    Optional<GameDetails> findGameDetailsByNameAndCompany(String name, String company);

    @Transactional
    @Modifying
    @Query("UPDATE GameDetails g SET " +
            "g.name = :name, " +
            "g.description = :description, " +
            "g.rating = :rating, " +
            "g.genre = :genre, " +
            "g.company = :company, " +
            "g.studio = :studio, " +
            "g.isFavourite = :isFavourite, " +
            "g.releaseDate = :releaseDate " +
            "WHERE g.id = :id")
    void updateGameDetails(Long id, String name, String description, Float rating,
                           String genre, String company, String studio, Boolean isFavourite, String releaseDate);

    @Override
    @Modifying
    @Query("DELETE FROM GameDetails g WHERE g.id = ?1")
    int deleteGameDetailsById(Long id);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE game_details_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
