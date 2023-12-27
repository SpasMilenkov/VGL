package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.dto.mapper.GameDtoMapper;
import com.jaba.vgl.models.entities.Company;

import java.util.List;

public record CompanyDto(
        String name,
        String studio,
        List<GameDto> games
) {
    public Company toEntity() {
        Company company = new Company();
        CompanyDto dto = this;

        company.setName(dto.name);
        company.setStudio(dto.studio);
        company.setGames(dto.games
                .stream()
                .map(GameDto::toEntity)
                .toList()
        );

        return company;
    }
}
