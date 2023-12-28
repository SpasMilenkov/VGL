package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;

import java.util.Optional;

public interface GameRepositoryCustom {

    void truncate();

    Optional<Long> getGameId(String name, Company company);

    void updateGame(Long id, String name, String description, Float rating,
                    GameGenre genre, Company company, Boolean isFavourite, String releaseDate);
}
