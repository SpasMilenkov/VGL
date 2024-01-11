package com.jaba.vgl.controllers;


import com.jaba.vgl.models.dto.JwtAuthenticationResponse;
import com.jaba.vgl.models.dto.RefreshTokenDto;
import com.jaba.vgl.models.dto.LoginDto;
import com.jaba.vgl.models.dto.RegisterDto;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> signUp(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }
    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = authenticationService.login(loginDto);

        // Create and add the access token cookie
        Cookie accessTokenCookie = new Cookie("AccessToken", jwtResponse.getToken());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true); // Set to true if using HTTPS
        accessTokenCookie.setPath("/"); // The path on which the cookie will be available
        response.addCookie(accessTokenCookie);

        // Create and add the refresh token cookie
        Cookie refreshTokenCookie = new Cookie("RefreshToken", jwtResponse.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true); // Set to true if using HTTPS
        refreshTokenCookie.setPath("/"); // The path on which the cookie will be available
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(jwtResponse.getUserId());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;

        // Extract refresh token from cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("RefreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No refresh token found");
        }

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setToken(refreshToken);

        JwtAuthenticationResponse jwtResponse = authenticationService.refreshToken(refreshTokenDto);

        if (jwtResponse != null) {
            // Set new access token and refresh token in cookies
            Cookie accessTokenCookie = new Cookie("AccessToken", jwtResponse.getToken());
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(true); // Set to true if using HTTPS
            accessTokenCookie.setPath("/"); // The path on which the cookie will be available
            response.addCookie(accessTokenCookie);

            // Create and add the refresh token cookie
            Cookie refreshTokenCookie = new Cookie("RefreshToken", jwtResponse.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(true); // Set to true if using HTTPS
            refreshTokenCookie.setPath("/"); // The path on which the cookie will be available
            response.addCookie(refreshTokenCookie);

            return ResponseEntity.ok(jwtResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        String refreshToken = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("RefreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No refresh token found");
        }
        boolean result = authenticationService.logout(refreshToken);
        if(result)
            return ResponseEntity.ok("Logged out successfully");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No refresh token found");
    }

}
