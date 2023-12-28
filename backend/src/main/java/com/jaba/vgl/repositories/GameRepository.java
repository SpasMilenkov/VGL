package com.jaba.vgl.repositories;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.custom.GameRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>{

    Optional<Game> findGameById(Long id);

    Optional<Game> findGameByName(String name);

    Optional<Game> findGameByNameAndCompany(String name, Company company);

    Optional<List<Game>> findGamesByGenre(GameGenre genre);

    int deleteGameById(Long id);

    int deleteGameByNameAndCompany(String name, Company company);
}
