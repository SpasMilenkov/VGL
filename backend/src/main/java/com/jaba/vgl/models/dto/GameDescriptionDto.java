package com.jaba.vgl.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GameDescriptionDto {
    private int gameId;
    private String name;
    private String bannerUrl;
    private String studio;
    //the steam api has several versions some games haven't uploaded banner url
    //the frontend should fall back to this image since it is used by the older games
    //that haven't updated ¯\_(ツ)_/¯
    private String headerUrl;
    private String shortDescription;
    private List<String> genres;
    private Date releaseDate;
}
