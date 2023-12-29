package com.jaba.vgl.models.dto.steam;

import lombok.Data;

import java.util.List;

@Data
public class SteamNewsResponse {
    private int appid;
    private List<SteamNewsItemDto> newsitems;
}
