package com.jaba.vgl.models.dto.steam;

import lombok.Data;

@Data
public class SteamGameDto {
    private int appid;
    private String name;
    private int playtime_2weeks;
    private int playtime_forever;
    private String img_icon_url;
    private String img_logo_url;
    private boolean has_community_visible_stats;
}
