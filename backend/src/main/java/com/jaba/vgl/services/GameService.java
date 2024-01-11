package com.jaba.vgl.services;

import com.jaba.vgl.models.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game saveGame(Game game);
    List<Game> getGameByIds(List<Long> ids) ;
    void deleteGame(Long id);
    void truncateTable();
}
