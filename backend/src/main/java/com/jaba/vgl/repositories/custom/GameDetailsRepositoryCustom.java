package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Review;

import java.util.List;

public interface GameDetailsRepositoryCustom {

    void truncate();

    void updateGameDetails(Long id, String name, String description, Float rating,
                           GameGenre genre, Company company, Boolean isFavourite, String releaseDate, List<Review> reviews);
}
