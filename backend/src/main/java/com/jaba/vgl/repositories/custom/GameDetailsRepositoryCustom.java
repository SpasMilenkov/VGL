package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.models.entities.Review;

import java.util.List;
import java.util.Optional;

public interface GameDetailsRepositoryCustom {

    void truncate();

    Optional<Long> getGameDetailsId(String name, Company company);

    void updateGameDetails(Long id, String name, String description,
                           Company company, List<Review> reviews);
}
