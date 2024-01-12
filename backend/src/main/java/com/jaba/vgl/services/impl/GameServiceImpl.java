package com.jaba.vgl.services.impl;


import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.dto.mapper.GameDtoMapper;
import com.jaba.vgl.models.dto.mapper.OwnedGameDtoMapper;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.impl.GameRepositoryImpl;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepositoryImpl gameRepository;
    private final OwnedGameDtoMapper ownedGameDtoMapper;
    private final GameDtoMapper gameDtoMapper;
    private final Random random = new Random();
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public GameServiceImpl(GameRepositoryImpl gameRepository,
                           OwnedGameDtoMapper ownedGameDtoMapper,
                           GameDtoMapper gameDtoMapper,
                           JdbcTemplate jdbcTemplate) {
        this.gameRepository = gameRepository;
        this.ownedGameDtoMapper = ownedGameDtoMapper;
        this.gameDtoMapper = gameDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game saveGame(Game game) {
        // Save the game entity to the database
        return gameRepository.save(game);
    }

    @Override
    public void saveGamesBulk(List<OwnedGameDto> gameDtoList, User user) {
        List<Game> processedGames = new ArrayList<>();

        for (OwnedGameDto gameDto : gameDtoList) {
            Game game = gameRepository.findBySteamId((long)gameDto.getGameId())
                    .orElseGet(() -> ownedGameDtoMapper.apply(gameDto));

            game.getUsers().add(user);
            processedGames.add(game);
        }

        gameRepository.saveAll(processedGames);
    }

    @Override
    public List<GameDto> getGamesByIds() {
        int numberOfGames = 30;
        long totalGames = gameRepository.count();
        Set<Long> randomIds = new HashSet<>();

        while (randomIds.size() < numberOfGames) {
            long randomId = Math.abs(random.nextLong()) % totalGames + 1;
            randomIds.add(randomId);
        }

        return gameRepository
                .findAllById(randomIds)
                .stream()
                .filter(g -> g.getSteamId() != 0)
                .map(gameDtoMapper)
                .toList();
    }


    @Override
    public void deleteGame(Long id) {
        // Delete the game entity by its ID
        gameRepository.deleteById(id);
    }

    @Override
    public void truncateTable() {
        gameRepository.truncate();

        String sqlStatement = "ALTER SEQUENCE game_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
