package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class NewsItemDto {
    private String id;
    private String title;
    private String url;
    private String author;
    private String contents;
    private String feedLabel;
    private long date;
    private String feedName;
    private int appid;
}
