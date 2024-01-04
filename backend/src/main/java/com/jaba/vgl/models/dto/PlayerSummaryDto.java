package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class PlayerSummaryDto {
    private String steamId;
    private String username;
    private String avatarUrl;
    private String profileurl;
}