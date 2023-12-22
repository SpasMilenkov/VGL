package com.jaba.vgl.services;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    GameDto getGame(Long id);

    GameDto getGame(String name);

    GameDto getGame(String name, String company);
    List<GameDto> getGamesByGenre(GameGenre genre);

    void updateGame(GameDto game);

    void createGame(GameDto game);

    int deleteGame(Long id);

    int deleteGame(String name, String company);

    void truncateTable();
}
