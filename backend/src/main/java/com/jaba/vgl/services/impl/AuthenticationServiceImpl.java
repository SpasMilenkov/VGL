package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.UserAlreadyExistsException;
import com.jaba.vgl.models.Role;
import com.jaba.vgl.models.dto.*;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.services.AuthenticationService;
import com.jaba.vgl.services.GameService;
import com.jaba.vgl.services.JWTService;
import com.jaba.vgl.services.clients.SteamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final SteamService steamService;
    private final GameService gameService;
    public User register(RegisterDto registerDto){
        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exists.");
        }
        
        User user = new User();

        user.setEmail(registerDto.getEmail());
        user.setNickname(registerDto.getNickName());
        user.setSteamId(registerDto.getSteamId());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepository.save(user);

        List<OwnedGameDto> gameLibrary =  steamService.getOwnedGames(user.getSteamId());
        gameService.saveGamesBulk(gameLibrary, user);
        return user;
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
        jwtAuthenticationResponse.setUserId(user.getId());
        jwtAuthenticationResponse.setSteamId(user.getSteamId());
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
