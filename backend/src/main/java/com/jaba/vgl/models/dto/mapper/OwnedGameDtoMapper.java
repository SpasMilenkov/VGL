package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Component
public class OwnedGameDtoMapper  implements Function<OwnedGameDto, Game>{
    @Override
    public Game apply(OwnedGameDto ownedGameDto) {
        Game game = new Game();
        game.setName(ownedGameDto.getName());
        game.setReleaseDate(new Date());
        game.setSteamId((long) ownedGameDto.getGameId());
        game.setStudioName(ownedGameDto.getStudio());
        game.setUsers(new HashSet<User>());
        return game;
    }
}
