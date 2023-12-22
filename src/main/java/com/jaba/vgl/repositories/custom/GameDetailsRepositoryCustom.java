package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.GameGenre;

public interface GameDetailsRepositoryCustom {

    void truncate();

    void updateGameDetails(Long id, String name, String description, Float rating, GameGenre genre,
                           String company, String studio, Boolean isFavourite, String releaseDate);
}
