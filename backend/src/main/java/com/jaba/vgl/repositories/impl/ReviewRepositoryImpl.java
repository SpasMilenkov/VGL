package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.repositories.ReviewRepository;
import com.jaba.vgl.repositories.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(
        rollbackFor = Exception.class,
        propagation = Propagation.REQUIRED
)
public interface ReviewRepositoryImpl extends ReviewRepository, ReviewRepositoryCustom {
    @Transactional
    @Modifying
    @Query("UPDATE Review r SET " +
            "r.gameId = :gameId, " +
            "r.title = :title, " +
            "r.text = :text, " +
            "r.rating = :rating " +
            "WHERE r.id = :id")
    @Override
    void updateReview(Long id, Long gameId, String title, String text, Integer rating);



    @Override
    @Query("SELECT r.id FROM Review r WHERE r.title = ?1")
    Optional<Long> getReviewId(String title);

    @Override
    @Modifying
    @Query("DELETE FROM Review r WHERE r.id = ?1")
    int deleteReviewById(Long id);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE review_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
