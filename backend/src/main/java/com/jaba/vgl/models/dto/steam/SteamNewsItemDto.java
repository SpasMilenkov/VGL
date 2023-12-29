package com.jaba.vgl.models.dto.steam;

import lombok.Data;

import java.util.List;

@Data
public class SteamNewsItemDto {
    private String gid;
    private String title;
    private String url;
    private boolean is_external_url;
    private String author;
    private String contents;
    private String feedlabel;
    private long date;
    private String feedname;
    private int feed_type;
    private int appid;
    private List<String> tags;
}
