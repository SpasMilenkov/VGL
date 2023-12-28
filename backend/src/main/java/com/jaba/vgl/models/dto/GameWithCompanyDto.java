package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.mapper.CompanyDtoMapper;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.services.impl.GameServiceImpl;

import java.util.Optional;

public record GameWithCompanyDto(
        GameDto gameDto,
        CompanyDto companyDto
) {
    public Game toEntity(GameServiceImpl gameService) {
        GameDto gameDto = this.gameDto;
        CompanyDto companyDto = this.companyDto;
        Game game = new Game();

        Optional<Long> id = gameService.getGameId(gameDto.name(), companyDto);
        id.ifPresent(game::setId);

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
