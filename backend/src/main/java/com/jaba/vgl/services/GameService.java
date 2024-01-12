package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game saveGame(Game game);
    void saveGamesBulk(List<OwnedGameDto> gameDtoList, User user);
    public List<GameDto> getGamesByIds();
    void deleteGame(Long id);
    void truncateTable();
}
