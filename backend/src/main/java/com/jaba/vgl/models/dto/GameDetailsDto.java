package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.GameDetails;

public record GameDetailsDto(
        String name,

        String description,

        Integer rating,

        GameGenre genre,

        CompanyDto company,

        Boolean isFavourite,

        String releaseDate
) {

    public GameDetails toEntity() {
        GameDetails gameDetails = new GameDetails();
        GameDetailsDto dto = this;

        gameDetails.setName(dto.name());
        gameDetails.setDescription(dto.description());
        gameDetails.setRating(dto.rating());
        gameDetails.setGenre(dto.genre());
        gameDetails.setIsFavourite(dto.isFavourite());
        gameDetails.setReleaseDate(dto.releaseDate());

        gameDetails.setCompany(dto.company().toEntity());
        gameDetails.setCompanyId(dto.company().id());

        return gameDetails;
    }
}