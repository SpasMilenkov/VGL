package com.jaba.vgl.repositories.custom;

import java.util.Optional;

public interface ReviewRepositoryCustom {

    void truncate();

    Optional<Long> getReviewId(String title);

    void updateReview(Long id, Long gameId, String title, String text, Integer rating);
}
