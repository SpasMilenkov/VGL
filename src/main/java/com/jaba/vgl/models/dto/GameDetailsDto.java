package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.GameDetails;

import java.util.List;

public record GameDetailsDto(
        String name,

        String description,

        Float rating,

        GameGenre genre,

        String company,

        String studio,

        Boolean isFavourite,

        String releaseDate,

        List<ReviewDto> reviews

//        List<GameDto> moreLikeThisList
) {

    public GameDetails toEntity() {
        GameDetails gameDetails = new GameDetails();
        GameDetailsDto dto = this;

        gameDetails.setName(dto.name());
        gameDetails.setDescription(dto.description());
        gameDetails.setRating(dto.rating());
        gameDetails.setGenre(dto.genre());
        gameDetails.setCompany(dto.company());
        gameDetails.setStudio(dto.studio());
        gameDetails.setIsFavourite(dto.isFavourite());
        gameDetails.setReleaseDate(dto.releaseDate());
        gameDetails.setReviews(dto.reviews
                .stream()
                .map(ReviewDto::toEntity)
                .toList()
        );

        return gameDetails;
    }
}