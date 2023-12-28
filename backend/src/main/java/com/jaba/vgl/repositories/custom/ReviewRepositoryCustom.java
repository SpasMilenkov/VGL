package com.jaba.vgl.repositories.custom;

import java.util.Optional;

public interface ReviewRepositoryCustom {

    void truncate();

    Optional<Long> getReviewId(String title);

    void updateReview(Long id, Long gameId, Long userId, String title, String text, Float rating);
}
