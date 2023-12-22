package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Game;

public interface GameRepositoryCustom {

    void truncate();

    void updateGame(Long id, String name, String description, Float rating, GameGenre genre, String company, String studio, Boolean isFavourite, String releaseDate);
}
