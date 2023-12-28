package com.jaba.vgl.util;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.*;
import com.jaba.vgl.models.entities.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MockupDataGenerator {

    public GameWithCompanyDto generateGameWithCompanyDto() {
        GameDto gameDto = generateGameDto();

        CompanyDto companyDto = generateCompanyDto();

        GameWithCompanyDto gameWithCompanyDto = new GameWithCompanyDto(
                gameDto,
                companyDto
        );

        return gameWithCompanyDto;
    }

    public CompanyDto generateCompanyDto() {
        List<GameDto> games = new ArrayList<>();
//        games.add(generateGameDto());

        CompanyDto companyDto = new CompanyDto(
                1L,
                "Blizzard",
                "",
                games
        );

        return companyDto;
    }

    public GameDto generateGameDto() {
        GameDto gameDto = new GameDto(
                "World of Warcraft - Wrath of Lich King.",
                "best game ever made.",
                5.0f,
                GameGenre.RPG,
                false,
                "02.04.2000"
        );

        return gameDto;
    }

    public GameDetailsDto generateGameDetailsDto() {
        CompanyDto companyDto = generateCompanyDto();

        GameDetailsDto gameDetailsDto = new GameDetailsDto(
                "World of Warcraft - Wrath of Lich King.",
                "best game ever made.",
                5.0f,
                GameGenre.RPG,
                companyDto,
                false,
                "02.04.2000"
        );

        return gameDetailsDto;
    }

    public ReviewDto generateReviewDto() {
        ReviewDto reviewDto = new ReviewDto(
                1L,
                1L,
                1L,
                "Good game. Would recommend",
                "Unreal graphics.",
                5.0f
        );

        return reviewDto;
    }

    public GameDetailsWithReviewsDto generateGameDetailsWithReviewDto() {
        List<ReviewDto> reviews = new ArrayList<ReviewDto>();
        reviews.add(generateReviewDto());

        GameDetailsWithReviewsDto gameDetailsWithReviewsDto = new GameDetailsWithReviewsDto(
                generateGameDetailsDto(),
                generateCompanyDto(),
                reviews
        );

        return gameDetailsWithReviewsDto;
    }
}
