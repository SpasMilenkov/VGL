package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.Review;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.models.entities.Wish;
import com.jaba.vgl.services.impl.ReviewServiceImpl;

import java.util.Date;

public record WishDto(
        Long gameId,
        Integer userId
) {
    public Wish toEntity(User user, Game game) {
        Wish wish = new Wish();
        wish.setDateAdded(new Date());
        wish.setGame(game);
        wish.setUser(user);

        return wish;
    }
}
