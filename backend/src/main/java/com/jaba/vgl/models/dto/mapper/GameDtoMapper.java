package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.entities.Game;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameDtoMapper implements Function<Game, GameDto> {
    @Override
    public GameDto apply(Game game) {
        return new GameDto(
                game.getName(),
                game.getDescription(),
                game.getRating(),
                game.getGenre(),
                game.getIsFavourite(),
                game.getReleaseDate()
        );
    }
}
