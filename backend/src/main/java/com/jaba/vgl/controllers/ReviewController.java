package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.ReviewDto;
import com.jaba.vgl.services.ReviewService;
import com.jaba.vgl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }
    @GetMapping("reviews-by-user-id/{id}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserReviews(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<Long>> getReviewId(@RequestParam String title) {
        return ResponseEntity.ok(reviewService.getReviewId(title));
    }

    @PostMapping
    public ResponseEntity<Void> saveReview(@RequestBody ReviewDto reviewDto) {
        reviewService.saveReview(reviewDto);
        return ResponseEntity.ok().build();
    }
}