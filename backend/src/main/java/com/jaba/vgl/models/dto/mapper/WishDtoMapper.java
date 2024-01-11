package com.jaba.vgl.models.dto.mapper;


import com.jaba.vgl.models.dto.WishDto;
import com.jaba.vgl.models.entities.Wish;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WishDtoMapper implements Function<Wish, WishDto> {

    @Override
    public WishDto apply(Wish wish) {
        Long gameId = wish.getGame() != null ? wish.getGame().getId() : null;
        Integer userId = wish.getUser() != null ? wish.getUser().getId() : null;

        return new WishDto(gameId, userId);
    }
}