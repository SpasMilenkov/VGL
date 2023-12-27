package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Game;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameWithCompanyDtoMapper implements Function<Game, GameWithCompanyDto> {

    private final GameDtoMapper gameDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;

    public GameWithCompanyDtoMapper(GameDtoMapper gameDtoMapper,
                                    CompanyDtoMapper companyDtoMapper) {
        this.gameDtoMapper = gameDtoMapper;
        this.companyDtoMapper = companyDtoMapper;
    }

    @Override
    public GameWithCompanyDto apply(Game game) {
        GameDto gameDto = gameDtoMapper.apply(game);
        Company company = game.getCompany();
        CompanyDto companyDto = companyDtoMapper.apply(company);

        return new GameWithCompanyDto(
                gameDto,
                companyDto
        );
    }
}
