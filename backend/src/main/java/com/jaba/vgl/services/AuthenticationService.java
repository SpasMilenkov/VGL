package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.JwtAuthenticationResponse;
import com.jaba.vgl.models.dto.RefreshTokenDto;
import com.jaba.vgl.models.dto.LoginDto;
import com.jaba.vgl.models.dto.RegisterDto;
import com.jaba.vgl.models.entities.User;

public interface AuthenticationService {
    User singUp(RegisterDto registerDto);
    JwtAuthenticationResponse signIn(LoginDto signInDto);
    JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto);
    boolean logout(String token);
}
