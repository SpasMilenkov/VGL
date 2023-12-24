package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Game;

public record GameDto(
        String name,

        String description,

        Float rating,

        GameGenre genre,

        String company,

        String studio,

        Boolean isFavourite,

        String releaseDate
) {
    public Game toEntity() {
        Game game = new Game();
        GameDto dto = this;

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setRating(dto.rating());
        game.setGenre(dto.genre());
        game.setCompany(dto.company());
        game.setStudio(dto.studio());
        game.setIsFavourite(dto.isFavourite());
        game.setReleaseDate(dto.releaseDate());
        return game;
    }
}