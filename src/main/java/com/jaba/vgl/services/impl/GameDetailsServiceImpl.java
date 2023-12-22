package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GameDetailsNotFoundException;
import com.jaba.vgl.models.dto.GameDetailsDto;
import com.jaba.vgl.models.dto.mapper.GameDetailsDtoMapper;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.repositories.GameDetailsRepository;
import com.jaba.vgl.repositories.impl.GameDetailsRepositoryImpl;
import com.jaba.vgl.services.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDetailsServiceImpl implements GameDetailsService {

    private final GameDetailsRepositoryImpl gameDetailsRepository;
    private final GameDetailsDtoMapper gameDetailsDtoMapper;

    @Autowired
    public GameDetailsServiceImpl(GameDetailsRepositoryImpl gameDetailsRepository,
                                  GameDetailsDtoMapper gameDetailsDtoMapper) {
        this.gameDetailsRepository = gameDetailsRepository;
        this.gameDetailsDtoMapper = gameDetailsDtoMapper;
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
    public GameDetailsDto getGameDetails(String name, String company) {
        return gameDetailsRepository.findGameDetailsByNameAndCompany(name, company)
                .stream()
                .map(gameDetailsDtoMapper)
                .findFirst()
                .orElseThrow(
                        () -> new GameDetailsNotFoundException(
                                String.format("Game details for game with name %s and company %s not found.",
                                        name,
                                        company
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
                gameDetails.getStudio(),
                gameDetails.getIsFavourite(),
                gameDetails.getReleaseDate()
        ); //gameDetails.getReviews()
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
    public int deleteGameDetails(String name, String company) {
        return gameDetailsRepository.deleteGameDetailsByNameAndCompany(name, company);
    }

    @Override
    public void truncateTable() {
        gameDetailsRepository.truncate();
    }
}
