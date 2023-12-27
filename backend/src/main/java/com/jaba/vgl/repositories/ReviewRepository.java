package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getReviewById(Long id);

    int deleteReviewById(Long id);
}
