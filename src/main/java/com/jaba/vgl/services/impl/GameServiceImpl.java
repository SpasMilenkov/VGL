package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.dto.mapper.GameDtoMapper;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.GameRepository;
import com.jaba.vgl.repositories.impl.GameRepositoryImpl;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepositoryImpl gameRepository;
    private final GameDtoMapper gameDtoMapper;

    @Autowired
    public GameServiceImpl(GameRepositoryImpl gameRepository,
                           GameDtoMapper gameDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
    }

    @Override
    public GameDto getGame(Long id) {
        return gameRepository.findGameById(id)
                .stream()
                .map(gameDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("Game with id %d not found.",
                                        id
                                )
                        )
                );
    }

    @Override
    public GameDto getGame(String name) {
        return gameRepository.findGameByName(name)
                .stream()
                .map(gameDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("Game with name %s not found.",
                                        name
                                )
                        )
                );
    }

    @Override
    public GameDto getGame(String name, String company) {
        return gameRepository.findGameByNameAndCompany(name, company)
                .stream()
                .map(gameDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("Game with name %s and company %s not found.",
                                        name,
                                        company
                                )
                        )
                );
    }

    @Override
    public List<GameDto> getGamesByGenre(GameGenre genre) {
        return gameRepository.findGamesByGenre(genre)
                .map(games -> games.stream()
                        .map(gameDtoMapper)
                        .collect(Collectors.toList())
                )
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("No games with genre %s were found.", genre)
                        )
                );
    }

    @Override
    public void updateGame(GameDto gameDto) {
        Game game = gameDto.toEntity();

        gameRepository.updateGame(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getRating(),
                game.getGenre(),
                game.getCompany(),
                game.getStudio(),
                game.getIsFavourite(),
                game.getReleaseDate()
        );
    }

    @Override
    public void createGame(GameDto gameDto) {
        Game game = gameDto.toEntity();

        gameRepository.save(game);
    }

    @Override
    public int deleteGame(Long id) {
        return gameRepository.deleteGameById(id);
    }

    @Override
    public int deleteGame(String name, String company) {
        return gameRepository.deleteGameByNameAndCompany(name, company);
    }

    @Override
    public void truncateTable() {
        gameRepository.truncate();
    }
}
