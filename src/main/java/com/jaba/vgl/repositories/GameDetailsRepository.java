package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.repositories.custom.GameDetailsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GameDetailsRepository extends JpaRepository<GameDetails, Long>, GameDetailsRepositoryCustom {

    Optional<GameDetails> findGameDetailsById(Long id);

    Optional<GameDetails> findGameDetailsByName(String name);

    Optional<GameDetails> findGameDetailsByNameAndCompany(String name, String company);

    int deleteGameDetailsById(Long id);
    int deleteGameDetailsByNameAndCompany(String name, String company);
}
