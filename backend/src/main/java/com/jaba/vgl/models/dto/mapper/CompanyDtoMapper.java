package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CompanyDtoMapper implements Function<Company, CompanyDto> {

    @Lazy
    private final GameDtoMapper gameDtoMapper;

    @Autowired
    public CompanyDtoMapper(GameDtoMapper gameDtoMapper) {
        this.gameDtoMapper = gameDtoMapper;
    }

    @Override
    public CompanyDto apply(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getStudio(),
                company.getGames()
                        .stream()
                        .map(gameDtoMapper)
                        .toList()
        );
    }
}
