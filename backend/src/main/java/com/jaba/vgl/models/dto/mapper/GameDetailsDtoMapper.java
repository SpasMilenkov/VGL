package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.entities.GameDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameDetailsDtoMapper implements Function<GameDetails, GameDetailsDto> {

    private final ReviewDtoMapper reviewDtoMapper;

    @Autowired
    public GameDetailsDtoMapper(ReviewDtoMapper reviewDtoMapper) {
        this.reviewDtoMapper = reviewDtoMapper;
    }

    @Override
    public GameDetailsDto apply(GameDetails game) {
        return new GameDetailsDto(
                game.getName(),
                game.getDescription(),
                game.getRating(),
                game.getGenre(),
                game.getCompany(),
                game.getStudio(),
                game.getIsFavourite(),
                game.getReleaseDate(),
                game.getReviews()
                        .stream()
                        .map(reviewDtoMapper)
                        .toList()
        );
    }
}
