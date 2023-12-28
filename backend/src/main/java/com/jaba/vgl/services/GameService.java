package com.jaba.vgl.services;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    GameWithCompanyDto getGame(Long id);

    Game getGameEntity(String name, CompanyDto companyDto);

    GameWithCompanyDto getGame(String name, CompanyDto companyDto);

    Optional<Long> getGameId(String name, CompanyDto companyDto);

    List<GameWithCompanyDto> getGamesByGenre(GameGenre genre);

    void updateGame(GameWithCompanyDto game);

    void createGame(GameWithCompanyDto game);

    int deleteGame(Long id);

    int deleteGame(String name, CompanyDto companyDto);

    void truncateTable();
}
