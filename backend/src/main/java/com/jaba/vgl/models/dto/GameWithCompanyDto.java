package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.mapper.CompanyDtoMapper;
import com.jaba.vgl.models.entities.Game;

public record GameWithCompanyDto(
        GameDto gameDto,
        CompanyDto companyDto
) {
    public Game toEntity() { //TODO: add find by name and company -> get id... (default -> null)
        Game game = new Game();
        GameDto gameDto = this.gameDto;
        CompanyDto companyDto = this.companyDto;

        game.setCompanyId(companyDto.id());
        game.setCompany(companyDto.toEntity());

        game.setName(gameDto.name());
        game.setDescription(gameDto.description());
        game.setRating(gameDto.rating());
        game.setGenre(gameDto.genre());
        game.setIsFavourite(gameDto.isFavourite());
        game.setReleaseDate(gameDto.releaseDate());

        return game;
    }
}
