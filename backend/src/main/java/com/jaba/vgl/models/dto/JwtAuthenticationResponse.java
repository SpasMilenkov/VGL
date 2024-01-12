package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private Integer userId;
    private Long steamId;
}
