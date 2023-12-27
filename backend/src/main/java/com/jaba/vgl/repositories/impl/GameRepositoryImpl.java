package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.GameRepository;
import com.jaba.vgl.repositories.custom.GameRepositoryCustom;
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
public interface GameRepositoryImpl extends GameRepository, GameRepositoryCustom {

    @Override
    @Query("SELECT g FROM Game g WHERE g.id = ?1")
    Optional<Game> findGameById(Long id);

    @Override
    @Query("SELECT g FROM Game g WHERE g.name = ?1")
    Optional<Game> findGameByName(String name);

    @Override
    @Query("SELECT g FROM Game g WHERE g.name = ?1 AND g.company = ?2")
    Optional<Game> findGameByNameAndCompany(String name, Company company);

    @Override
    @Query("SELECT g FROM Game g WHERE g.genre = ?1")
    Optional<List<Game>> findGamesByGenre(GameGenre genre);

    @Transactional
    @Modifying
    @Query("UPDATE Game g SET " +
            "g.name = :name, " +
            "g.description = :description, " +
            "g.rating = :rating, " +
            "g.genre = :genre, " +
            "g.company = :company, " +
            "g.isFavourite = :isFavourite, " +
            "g.releaseDate = :releaseDate " +
            "WHERE g.id = :id")
    void updateGame(Long id, String name, String description, Float rating,
                    GameGenre genre, Company company, Boolean isFavourite, String releaseDate);

    @Override
    @Modifying
    @Query("DELETE FROM Game g WHERE g.id = ?1")
    int deleteGameById(Long id);


    @Override
    @Modifying
    @Query("DELETE FROM Game g WHERE g.name = ?1 AND g.company = ?2")
    int deleteGameByNameAndCompany(String name, Company company);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE game_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
