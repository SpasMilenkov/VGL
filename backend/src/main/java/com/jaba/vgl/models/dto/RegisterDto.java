package com.jaba.vgl.models.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String nickName;
    private String steamId;
    private String email;
    private String password;

}
