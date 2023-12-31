package com.jaba.vgl.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OwnedGameDto {
    private int gameId;
    private String name;
    private int playtimeForever;
    private String bannerUrl;
    private String shortDescription;
    private List<String> genres;
    private Date releaseDate;
}
