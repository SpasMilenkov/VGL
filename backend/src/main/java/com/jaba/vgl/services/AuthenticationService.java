package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.JwtAuthenticationResponse;
import com.jaba.vgl.models.dto.RefreshTokenDto;
import com.jaba.vgl.models.dto.LoginDto;
import com.jaba.vgl.models.dto.RegisterDto;
import com.jaba.vgl.models.entities.User;

public interface AuthenticationService {
    User register(RegisterDto registerDto);
    JwtAuthenticationResponse login(LoginDto signInDto);
    JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto);
    boolean logout(String token);
}
