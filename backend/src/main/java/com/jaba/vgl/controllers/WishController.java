package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.WishDto;
import com.jaba.vgl.models.entities.Wish;
import com.jaba.vgl.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishes")
public class WishController {

    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    public ResponseEntity<WishDto> createWish(@RequestBody WishDto wish) {
        return ResponseEntity.ok(wishService.saveWish(wish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wish> getWishById(@PathVariable Long id) {
        return ResponseEntity.ok(wishService.getWishById(id));
    }

    @DeleteMapping("/delete")
    public void deleteWish(@RequestParam Long userId, @RequestParam Long gameId) {
        wishService.deleteWish(userId, gameId);
    }
}
