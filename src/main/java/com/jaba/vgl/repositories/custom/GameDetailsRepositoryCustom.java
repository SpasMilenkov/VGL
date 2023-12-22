package com.jaba.vgl.repositories.custom;

public interface GameDetailsRepositoryCustom {

    void truncate();

    void updateGameDetails(Long id, String name, String description, Float rating, String genre, String company, String studio, Boolean isFavourite, String releaseDate);
}
