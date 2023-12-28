package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameDetailsNotFoundException;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.GameDetailsWithReviewsDto;
import com.jaba.vgl.models.dto.mapper.GameDetailsDtoMapper;
import com.jaba.vgl.models.dto.mapper.GameDetailsWithReviewsDtoMapper;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.repositories.impl.GameDetailsRepositoryImpl;
import com.jaba.vgl.services.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDetailsServiceImpl implements GameDetailsService {

    private final GameDetailsRepositoryImpl gameDetailsRepository;
    private final ReviewServiceImpl reviewService;
    private final GameDetailsDtoMapper gameDetailsDtoMapper;
    private final GameDetailsWithReviewsDtoMapper gameDetailsWithReviewsDtoMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDetailsServiceImpl(GameDetailsRepositoryImpl gameDetailsRepository,
                                  ReviewServiceImpl reviewService,
                                  GameDetailsDtoMapper gameDetailsDtoMapper,
                                  GameDetailsWithReviewsDtoMapper gameDetailsWithReviewsDtoMapper,
                                  JdbcTemplate jdbcTemplate) {
        this.gameDetailsRepository = gameDetailsRepository;
        this.reviewService = reviewService;
        this.gameDetailsDtoMapper = gameDetailsDtoMapper;
        this.gameDetailsWithReviewsDtoMapper = gameDetailsWithReviewsDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GameDetailsWithReviewsDto getGameDetails(Long id) {
        return gameDetailsRepository.findGameDetailsById(id)
                .stream()
                .map(gameDetailsWithReviewsDtoMapper)
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
    public GameDetailsWithReviewsDto getGameDetails(String name) {
        return gameDetailsRepository.findGameDetailsByName(name)
                .stream()
                .map(gameDetailsWithReviewsDtoMapper)
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
    public GameDetailsWithReviewsDto getGameDetails(String name, CompanyDto companyDto) {
        return gameDetailsRepository.findGameDetailsByNameAndCompany(name, companyDto.toEntity())
                .stream()
                .map(gameDetailsWithReviewsDtoMapper)
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
    public List<GameDetailsWithReviewsDto> getGameDetails() {
        return gameDetailsRepository.findAll()
                .stream()
                .map(gameDetailsWithReviewsDtoMapper)
                .toList();
    }

    @Override
    public void updateGameDetails(GameDetailsWithReviewsDto gameDetailsDto) {
        GameDetails gameDetails = gameDetailsDto.toEntity(reviewService);

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
    public void createGameDetails(GameDetailsWithReviewsDto gameDetailsDto) {
        GameDetails gameDetails = gameDetailsDto.toEntity(reviewService);

        gameDetailsRepository.save(gameDetails);
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

        String sqlStatement = "ALTER SEQUENCE game_details_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
