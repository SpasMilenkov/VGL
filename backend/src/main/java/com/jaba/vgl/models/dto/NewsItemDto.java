package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class NewsItemDto {
    private int appid;
    private String title;
    private String url;
    private String author;
    private String contents;
}
