package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class OwnedGameDto {
    private int gameId;
    private String name;
    private String studio;
    private String trailerUrl;
    private String bannerUrl;
    //the steam api has several versions some games haven't uploaded banner url
    //the frontend should fall back to this image since it is used by the older games
    //that haven't updated ¯\_(ツ)_/¯
    private String headerUrl;
}
