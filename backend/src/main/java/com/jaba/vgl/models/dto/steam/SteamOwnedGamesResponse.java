package com.jaba.vgl.models.dto.steam;

import com.jaba.vgl.models.dto.OwnedGameDto;
import lombok.Data;

import java.util.List;

@Data
public class SteamOwnedGamesResponse {
    private int game_count;
    private List<OwnedGameDto> games;
}
