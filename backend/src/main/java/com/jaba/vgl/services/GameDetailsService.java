package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.entities.Company;

public interface GameDetailsService {

    GameDetailsDto getGameDetails(Long id);

    GameDetailsDto getGameDetails(String name);

    GameDetailsDto getGameDetails(String name, CompanyDto companyDto);

    void updateGameDetails(GameDetailsDto gameDetailsDto);

    void createGameDetails(GameDetailsDto gameDetailsDto);

    int deleteGameDetails(Long id);

    int deleteGameDetails(String name, CompanyDto companyDto);

    void truncateTable();
}
