package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.GameGenre;

public interface ReviewRepositoryCustom {

    void truncate();

    void updateReview(Long id, Long gameId, String title, String text, Float rating);
}
