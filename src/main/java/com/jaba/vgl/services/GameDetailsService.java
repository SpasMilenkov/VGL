package com.jaba.vgl.services;

import com.jaba.vgl.models.entities.GameDetails;

import java.util.List;

public interface GameDetailsService {

    List<GameDetails> getGameDetails(Long id);

    List<GameDetails> getGameDetails(String name);

    List<GameDetails> getGameDetails(String name, String company);

    void updateGameDetails(GameDetails game);

    void createGameDetails(GameDetails game);

    int deleteGameDetails(Long id);

    int deleteGameDetails(String name, String company);

    void truncateTable();
}
