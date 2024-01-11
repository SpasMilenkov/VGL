package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.Game;

public record GameDto(
        String name,
        String releaseDate
) {
    public Game toEntity() {
        Game game = new Game();
        GameDto dto = this;

        game.setName(dto.name());
        game.setReleaseDate(dto.releaseDate());

        return game;
    }
}
