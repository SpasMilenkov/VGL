package com.jaba.vgl.models.dto.steam;

import lombok.Data;

@Data
public class SteamAchievementDto {
    private String apiname;
    private boolean achieved;
    private long unlocktime;
    private String name; // Optional: Localized achievement name
    private String description; // Optional: Localized description of the achievement

}
