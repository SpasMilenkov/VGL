package com.jaba.vgl.services;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;

import java.util.List;

public interface GameService {

    GameWithCompanyDto getGame(Long id);

    GameWithCompanyDto getGame(String name);

    GameWithCompanyDto getGame(String name, CompanyDto companyDto);
    List<GameWithCompanyDto> getGamesByGenre(GameGenre genre);

    void updateGame(GameWithCompanyDto game);

    void createGame(GameWithCompanyDto game);

    int deleteGame(Long id);

    int deleteGame(String name, CompanyDto companyDto);

    void truncateTable();
}
