package com.jaba.vgl.models.dto.steam;

import lombok.Data;

import java.util.List;

@Data
public class SteamAchievementsResponse {
    private List<SteamAchievementDto> achievements;
}
