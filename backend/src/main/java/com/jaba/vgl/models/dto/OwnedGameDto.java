package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class OwnedGameDto {
    private int gameId;
    private String name;
    private int playtimeForever;
}
