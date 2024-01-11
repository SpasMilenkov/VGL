package com.jaba.vgl.services.impl;

import com.jaba.vgl.models.entities.Wish;
import com.jaba.vgl.repositories.WishRepository;
import com.jaba.vgl.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    @Autowired
    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public Wish saveWish(Wish wish) {
        return wishRepository.save(wish);
    }

    @Override
    public Wish getWishById(Long id) {
        return wishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wish not found"));
    }

    @Override
    public List<Wish> getWishByGameId(Long gameId) {
        return wishRepository.findWishByGameId(gameId);
    }


    @Override
    public void deleteWish(Long id) {
        wishRepository.deleteById(id);
    }
}
