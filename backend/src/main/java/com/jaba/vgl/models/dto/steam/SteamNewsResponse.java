package com.jaba.vgl.models.dto.steam;

import com.jaba.vgl.models.dto.NewsItemDto;
import lombok.Data;

import java.util.List;

@Data
public class SteamNewsResponse {
    private int appid;
    private List<NewsItemDto> newsitems;
}
