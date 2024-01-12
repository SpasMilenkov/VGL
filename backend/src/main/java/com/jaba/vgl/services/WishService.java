package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.WishDto;
import com.jaba.vgl.models.entities.Wish;

import java.util.List;

public interface WishService {
    WishDto saveWish(WishDto wish);
    Wish getWishById(Long id);
    List<Wish> getWishByGameId(Long gameId);
    void deleteWish(Long userId, Long gameId);
}
