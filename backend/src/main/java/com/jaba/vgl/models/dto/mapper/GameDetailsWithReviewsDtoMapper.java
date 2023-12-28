package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.GameDetailsWithReviewsDto;
import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.GameDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class GameDetailsWithReviewsDtoMapper implements Function<GameDetails, GameDetailsWithReviewsDto> {

    private final ReviewDtoMapper reviewDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;
    private final GameDetailsDtoMapper gameDetailsDtoMapper;

    @Autowired
    public GameDetailsWithReviewsDtoMapper(ReviewDtoMapper reviewDtoMapper,
                                           CompanyDtoMapper companyDtoMapper,
                                           GameDetailsDtoMapper gameDetailsDtoMapper) {
        this.reviewDtoMapper = reviewDtoMapper;
        this.companyDtoMapper = companyDtoMapper;
        this.gameDetailsDtoMapper = gameDetailsDtoMapper;
    }

    @Override
    public GameDetailsWithReviewsDto apply(GameDetails gameDetails) {
        GameDetailsDto gameDetailsDto = gameDetailsDtoMapper.apply(gameDetails);

        CompanyDto companyDto = gameDetailsDto.company();

        List<ReviewDto> reviews = gameDetails.getReviews()
                .stream()
                .map(reviewDtoMapper)
                .toList();

        return new GameDetailsWithReviewsDto(
                gameDetailsDto,
                companyDto,
                reviews
        );
    }
}
