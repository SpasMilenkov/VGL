package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameDetailsNotFoundException;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.mapper.GameDetailsDtoMapper;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.repositories.impl.GameDetailsRepositoryImpl;
import com.jaba.vgl.services.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameDetailsServiceImpl implements GameDetailsService {

    private final GameDetailsRepositoryImpl gameDetailsRepository;
    private final GameDetailsDtoMapper gameDetailsDtoMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDetailsServiceImpl(GameDetailsRepositoryImpl gameDetailsRepository,
                                  GameDetailsDtoMapper gameDetailsDtoMapper,
                                  JdbcTemplate jdbcTemplate) {
        this.gameDetailsRepository = gameDetailsRepository;
        this.gameDetailsDtoMapper = gameDetailsDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GameDetailsDto getGameDetails(Long id) {
        return gameDetailsRepository.findGameDetailsById(id)
                .stream()
                .map(gameDetailsDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameDetailsNotFoundException(
                                String.format("Game details for game with id %d not found.",
                                        id
                                )
                        )
                );
    }

    @Override
    public GameDetailsDto getGameDetails(String name) {
        return gameDetailsRepository.findGameDetailsByName(name)
                .stream()
                .map(gameDetailsDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameDetailsNotFoundException(
                                String.format("Game details for game with name %s not found.",
                                        name
                                )
                        )
                );
    }

    @Override
    public GameDetailsDto getGameDetails(String name, CompanyDto companyDto) {
        return gameDetailsRepository.findGameDetailsByNameAndCompany(name, companyDto.toEntity())
                .stream()
                .map(gameDetailsDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameDetailsNotFoundException(
                                String.format("Game details for game with name %s and company %s not found.",
                                        name,
                                        companyDto
                                )
                        )
                );
    }

    @Override
    public void updateGameDetails(GameDetailsDto gameDetailsDto) {
        GameDetails gameDetails = gameDetailsDto.toEntity();

        gameDetailsRepository.updateGameDetails(
                gameDetails.getId(),
                gameDetails.getName(),
                gameDetails.getDescription(),
                gameDetails.getRating(),
                gameDetails.getGenre(),
                gameDetails.getCompany(),
                gameDetails.getIsFavourite(),
                gameDetails.getReleaseDate(),
                gameDetails.getReviews()
        );
    }

    @Override
    public void createGameDetails(GameDetailsDto gameDetailsDto) {
        GameDetails gameDetails = gameDetailsDto.toEntity();

        gameDetailsRepository.save(gameDetails);
    }

    @Override
    public int deleteGameDetails(Long id) {
        return gameDetailsRepository.deleteGameDetailsById(id);
    }

    @Override
    public int deleteGameDetails(String name, CompanyDto companyDto) {
        return gameDetailsRepository.deleteGameDetailsByNameAndCompany(name, companyDto.toEntity());
    }

    @Override
    public void truncateTable() {
        gameDetailsRepository.truncate();

        String sqlStatement = "ALTER SEQUENCE vgl_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
