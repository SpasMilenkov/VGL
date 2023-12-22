package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.repositories.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    //Fetch by user id in GameDetailsRepo...

    int deleteReviewById(Long id);
}
