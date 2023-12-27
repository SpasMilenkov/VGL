package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.dto.mapper.GameDtoMapper;
import com.jaba.vgl.models.dto.mapper.GameWithCompanyDtoMapper;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.impl.GameRepositoryImpl;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepositoryImpl gameRepository;
    private final GameDtoMapper gameDtoMapper;
    private final GameWithCompanyDtoMapper gameWithCompanyDtoMapper;

    @Autowired
    public GameServiceImpl(GameRepositoryImpl gameRepository,
                           GameDtoMapper gameDtoMapper,
                           GameWithCompanyDtoMapper gameWithCompanyDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
        this.gameWithCompanyDtoMapper = gameWithCompanyDtoMapper;
    }

    @Override
    public GameWithCompanyDto getGame(Long id) {
        return gameRepository.findGameById(id)
                .stream()
                .map(gameWithCompanyDtoMapper)
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
    public GameWithCompanyDto getGame(String name) {
        return gameRepository.findGameByName(name)
                .stream()
                .map(gameWithCompanyDtoMapper)
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
    public GameWithCompanyDto getGame(String name, CompanyDto companyDto) {
        return gameRepository.findGameByNameAndCompany(name, companyDto.toEntity())
                .stream()
                .map(gameWithCompanyDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("Game with name %s and company %s not found.",
                                        name,
                                        companyDto
                                )
                        )
                );
    }

    @Override
    public List<GameWithCompanyDto> getGamesByGenre(GameGenre genre) {
        return gameRepository.findGamesByGenre(genre)
                .map(games -> games.stream()
                        .map(gameWithCompanyDtoMapper)
                        .collect(Collectors.toList())
                )
                .orElseThrow(
                        () -> new GameNotFoundException(
                                String.format("No games with genre %s were found.", genre)
                        )
                );
    }

    @Override
    public void updateGame(GameWithCompanyDto gameWithCompanyDto) {
        Game game = gameWithCompanyDto.toEntity();

        gameRepository.updateGame(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getRating(),
                game.getGenre(),
                game.getCompany(),
                game.getIsFavourite(),
                game.getReleaseDate()
        );
    }

    @Override
    @Transactional
    public void createGame(GameWithCompanyDto gameDto) {
        Game game = gameDto.toEntity();
        game.setCompany(game.getCompany());

        gameRepository.save(game);
    }

    @Override
    public int deleteGame(Long id) {
        return gameRepository.deleteGameById(id);
    }

    @Override
    public int deleteGame(String name, CompanyDto companyDto) {
        return gameRepository.deleteGameByNameAndCompany(name, companyDto.toEntity());
    }

    @Override
    public void truncateTable() {
        gameRepository.truncate();
    }
}
