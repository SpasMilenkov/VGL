package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.GameDetailsWithReviewsDto;
import com.jaba.vgl.models.entities.Company;

import java.util.List;

public interface GameDetailsService {

    GameDetailsWithReviewsDto getGameDetails(Long id);

    GameDetailsWithReviewsDto getGameDetails(String name);

    GameDetailsWithReviewsDto getGameDetails(String name, CompanyDto companyDto);

    List<GameDetailsWithReviewsDto> getGameDetails();

    void updateGameDetails(GameDetailsWithReviewsDto gameDetailsDto);

    void createGameDetails(GameDetailsWithReviewsDto gameDetailsDto);

    void createGameDetails(GameDetailsDto gameDetailsDto);

    int deleteGameDetails(Long id);

    int deleteGameDetails(String name, CompanyDto companyDto);

    void truncateTable();
}
