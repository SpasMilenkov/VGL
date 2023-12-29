package com.jaba.vgl.models.dto.steam;

import lombok.Data;

import java.util.List;

@Data
public class SteamPlayerSummariesResponse {
    private List<SteamPlayerSummaryDto> players;
}
