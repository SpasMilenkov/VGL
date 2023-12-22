package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.GameDetailsDto;

public interface GameDetailsService {

    GameDetailsDto getGameDetails(Long id);

    GameDetailsDto getGameDetails(String name);

    GameDetailsDto getGameDetails(String name, String company);

    void updateGameDetails(GameDetailsDto gameDetailsDto);

    void createGameDetails(GameDetailsDto gameDetailsDto);

    int deleteGameDetails(Long id);

    int deleteGameDetails(String name, String company);

    void truncateTable();
}
