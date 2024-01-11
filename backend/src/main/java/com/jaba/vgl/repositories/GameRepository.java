package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>{

    Optional<Game> findGameById(Long id);

    Optional<Game> findGameByNameAndCompany(String name, Company company);


    int deleteGameById(Long id);

    int deleteGameByNameAndCompany(String name, Company company);
}
