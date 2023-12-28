package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.services.impl.GameDetailsServiceImpl;
import com.jaba.vgl.services.impl.ReviewServiceImpl;

import java.util.List;
import java.util.Optional;

public record GameDetailsWithReviewsDto(
        GameDetailsDto gameDetailsDto,

        CompanyDto companyDto,

        List<ReviewDto> reviews
) {

    public GameDetails toEntity(ReviewServiceImpl reviewService, GameDetailsServiceImpl gameDetailsService) {
        GameDetails gameDetails = new GameDetails();
        GameDetailsDto gameDetailsDto = this.gameDetailsDto;
        CompanyDto companyDto = this.companyDto;
        List<ReviewDto> reviews = this.reviews;

        Optional<Long> id = gameDetailsService.getGameDetailsId(gameDetailsDto.name(), companyDto);
        id.ifPresent(gameDetails::setId);

        gameDetails.setName(gameDetailsDto.name());
        gameDetails.setDescription(gameDetailsDto.description());
        gameDetails.setRating(gameDetailsDto.rating());
        gameDetails.setGenre(gameDetailsDto.genre());
        gameDetails.setIsFavourite(gameDetailsDto.isFavourite());
        gameDetails.setReleaseDate(gameDetailsDto.releaseDate());

        gameDetails.setCompany(companyDto.toEntity());
        gameDetails.setCompanyId(companyDto.id());

        gameDetails.setReviews(reviews
                .stream()
                .map(reviewDto -> reviewDto.toEntity(reviewService))
                .toList()
        );

        return gameDetails;
    }
}