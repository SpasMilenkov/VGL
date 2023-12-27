package com.jaba.vgl.models.dto;

import com.jaba.vgl.exceptions.CompanyNotFoundException;
import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.CompanyRepository;
import com.jaba.vgl.services.CompanyService;

public record GameDto(
        String name,
        String description,
        Float rating,
        GameGenre genre,
        CompanyDto company,
        Boolean isFavourite,
        String releaseDate
) {
    public Game toEntity() {
        Game game = new Game();
        GameDto dto = this;
        Company company = dto.company().toEntity();

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setRating(dto.rating());
        game.setGenre(dto.genre());
        game.setCompany(company);
        game.setCompanyId(company.id);
        game.setIsFavourite(dto.isFavourite());
        game.setReleaseDate(dto.releaseDate());

        return game;
    }
}
