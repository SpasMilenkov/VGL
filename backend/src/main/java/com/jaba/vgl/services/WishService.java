package com.jaba.vgl.services;

import com.jaba.vgl.models.entities.Wish;

import java.util.List;

public interface WishService {
    Wish saveWish(Wish wish);
    Wish getWishById(Long id);
    List<Wish> getWishByGameId(Long gameId);
    void deleteWish(Long id);
}
