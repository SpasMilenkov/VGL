package com.jaba.vgl.models.dto.steam;

import lombok.Data;

import java.util.List;

@Data
public class SteamOwnedGamesResponse {
    private int game_count;
    private List<SteamGameDto> games;
}
