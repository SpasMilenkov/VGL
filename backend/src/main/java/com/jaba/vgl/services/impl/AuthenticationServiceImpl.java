package com.jaba.vgl.services.impl;

import com.jaba.vgl.models.Role;
import com.jaba.vgl.models.dto.JwtAuthenticationResponse;
import com.jaba.vgl.models.dto.RefreshTokenDto;
import com.jaba.vgl.models.dto.LoginDto;
import com.jaba.vgl.models.dto.RegisterDto;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.services.AuthenticationService;
import com.jaba.vgl.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public User register(RegisterDto registerDto){
        User user = new User();

        user.setEmail(registerDto.getEmail());
        user.setNickname(registerDto.getNickName());
        user.setSteamId(registerDto.getSteamId());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse login(LoginDto loginDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(IllegalArgumentException::new);
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(accessToken);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto){

        User user = userService.loadUserByRefreshToken(refreshTokenDto.getToken());
        if(user == null)
            return null;
        if(jwtService.isTokenValid(refreshTokenDto.getToken(), user)){
            var accessToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<String, Object>(), user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(accessToken);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            return jwtAuthenticationResponse;
        }
        return null;
    }
    public boolean logout(String token){
        User user = userService.loadUserByRefreshToken(token);
        if(user == null)
            return true;
        user.setRefreshToken(null);
        userRepository.save(user);
        return true;
    }

}
