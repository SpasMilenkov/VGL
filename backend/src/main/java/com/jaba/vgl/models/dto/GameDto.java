package com.jaba.vgl.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jaba.vgl.models.entities.Game;

import java.util.Date;

public record GameDto(
        String name,
        Long userId,
        Long steamId,
        @JsonFormat(pattern="yyyy")
        Date releaseDate,
        String studioName

) {
    public Game toEntity() {
        Game game = new Game();
        GameDto dto = this;

        game.setName(dto.name());

        return game;
    }
}
