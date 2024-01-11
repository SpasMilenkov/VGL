package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.models.dto.mapper.GameDtoMapper;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.impl.GameRepositoryImpl;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepositoryImpl gameRepository;
    private final GameDtoMapper gameDtoMapper;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public GameServiceImpl(GameRepositoryImpl gameRepository,
                           GameDtoMapper gameDtoMapper,
                           JdbcTemplate jdbcTemplate) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game saveGame(Game game) {
        // Save the game entity to the database
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getGameByIds(List<Long> ids) {
        return gameRepository.findByGameIds(ids);
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
