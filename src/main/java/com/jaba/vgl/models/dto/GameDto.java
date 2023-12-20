package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.GameGenre;

public record GameDto(
        String name,

        Float rating,

        GameGenre genre,

        String company,

        String studio,

        Boolean isFavourite,

        String releaseDate
) {
}