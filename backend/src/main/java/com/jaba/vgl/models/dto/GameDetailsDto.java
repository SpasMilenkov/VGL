package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.GameDetails;

public record GameDetailsDto(
        String name,

        String description,

        CompanyDto company
) {

    public GameDetails toEntity() {
        GameDetails gameDetails = new GameDetails();
        GameDetailsDto dto = this;

        gameDetails.setName(dto.name());
        gameDetails.setDescription(dto.description());

        gameDetails.setCompany(dto.company().toEntity());
        gameDetails.setCompanyId(dto.company().id());

        return gameDetails;
    }
}