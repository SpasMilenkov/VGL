package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameDetailsRepository extends JpaRepository<GameDetails, Long> {

    Optional<GameDetails> findGameDetailsById(Long id);

    Optional<GameDetails> findGameDetailsByNameAndCompany(String name, Company company);

    int deleteGameDetailsById(Long id);
    int deleteGameDetailsByNameAndCompany(String name, Company company);
}
