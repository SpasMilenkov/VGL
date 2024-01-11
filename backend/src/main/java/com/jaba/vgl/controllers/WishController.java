package com.jaba.vgl.controllers;

import com.jaba.vgl.models.entities.Wish;
import com.jaba.vgl.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody Wish wish) {
        return ResponseEntity.ok(wishService.saveWish(wish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wish> getWishById(@PathVariable Long id) {
        return ResponseEntity.ok(wishService.getWishById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wish> updateWish(@PathVariable Long id, @RequestBody Wish wish) {
        return ResponseEntity.ok(wishService.saveWish(wish));
    }
    //Endpoint that returns all wishes for a current game
    @DeleteMapping("/{id}")
    public void deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
    }
}
