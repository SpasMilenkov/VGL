package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;

import java.util.List;

public record GameDetailsDto(
        String name,

        Float rating,

        GameGenre genre,

        String company,

        String studio,

        Boolean isFavourite,

        String releaseDate,

        List<GameDto> moreLikeThisList

        ) {
}