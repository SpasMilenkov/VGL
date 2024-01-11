package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.entities.Company;

import java.util.Optional;

public interface GameRepositoryCustom {

    void truncate();

    Optional<Long> getGameId(String name, Company company);

    void updateGame(Long id, String name, Company company, String releaseDate);
}
