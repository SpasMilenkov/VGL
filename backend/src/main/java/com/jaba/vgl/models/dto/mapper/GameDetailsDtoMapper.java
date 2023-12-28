package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.GameDetailsWithReviewsDto;
import com.jaba.vgl.models.entities.GameDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameDetailsDtoMapper implements Function<GameDetails, GameDetailsDto> {

    private final ReviewDtoMapper reviewDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;

    @Autowired
    public GameDetailsDtoMapper(ReviewDtoMapper reviewDtoMapper,
                                CompanyDtoMapper companyDtoMapper) {
        this.reviewDtoMapper = reviewDtoMapper;
        this.companyDtoMapper = companyDtoMapper;
    }

    @Override
    public GameDetailsDto apply(GameDetails game) {
        return new GameDetailsDto(
                game.getName(),
                game.getDescription(),
                game.getRating(),
                game.getGenre(),
                companyDtoMapper.apply(game.getCompany()),
                game.getIsFavourite(),
                game.getReleaseDate()
        );
    }
}
