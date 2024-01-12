package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.GamesNotFoundException;
import com.jaba.vgl.exceptions.UserNotFoundException;
import com.jaba.vgl.models.dto.WishDto;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.models.entities.Wish;
import com.jaba.vgl.repositories.GameRepository;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.repositories.WishRepository;
import com.jaba.vgl.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public WishServiceImpl(WishRepository wishRepository,
                           UserRepository userRepository,
                           GameRepository gameRepository) {
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public WishDto saveWish(WishDto wish) {
        User user = userRepository.findById((long)wish.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Game game = gameRepository.findBySteamId(wish.gameId())
                .orElseThrow(() -> new GamesNotFoundException("Game not found"));

        wishRepository.save(wish.toEntity(user, game));

        return wish;
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
    @Transactional
    public void deleteWish(Long userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Game game = gameRepository.findBySteamId(gameId)
                .orElseThrow(() -> new GamesNotFoundException("Game not found"));

        wishRepository.deleteByUserAndGame(user, game);
    }
}
