package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.GameDetailsRepository;
import com.jaba.vgl.repositories.custom.GameDetailsRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(
        rollbackFor = Exception.class,
        propagation = Propagation.REQUIRED
)
public interface GameDetailsRepositoryImpl extends GameDetailsRepository, GameDetailsRepositoryCustom {

    @Override
    @Query("SELECT g FROM GameDetails g WHERE g.id = ?1")
    Optional<GameDetails> findGameDetailsById(Long id);

    @Override
    @Query("SELECT g FROM GameDetails g WHERE g.name = ?1 AND g.company = ?2")
    Optional<GameDetails> findGameDetailsByNameAndCompany(String name, Company company);

    @Override
    @Query("SELECT g.id FROM GameDetails g WHERE g.name = ?1 AND g.company = ?2")
    Optional<Long> getGameDetailsId(String name, Company company);

    @Transactional
    @Modifying
    @Override
    @Query("UPDATE GameDetails g SET " +
            "g.name = :name, " +
            "g.description = :description, " +
            "g.rating = :rating, " +
            "g.genre = :genre, " +
            "g.company = :company, " +
            "g.isFavourite = :isFavourite, " +
            "g.releaseDate = :releaseDate, " +
            "g.reviews = :reviews " +
            "WHERE g.id = :id")
    void updateGameDetails(Long id, String name, String description, Float rating,
                           GameGenre genre, Company company, Boolean isFavourite, String releaseDate, List<Review> reviews);

    @Override
    @Modifying
    @Query("DELETE FROM GameDetails g WHERE g.id = ?1")
    int deleteGameDetailsById(Long id);

    @Override
    @Modifying
    @Query("DELETE FROM GameDetails g WHERE g.name = ?1 AND g.company = ?2")
    int deleteGameDetailsByNameAndCompany(String name, Company company);


    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE game_details_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
