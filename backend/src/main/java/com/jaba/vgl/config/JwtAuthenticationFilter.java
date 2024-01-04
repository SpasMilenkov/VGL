package com.jaba.vgl.config;

import com.jaba.vgl.services.JWTService;
import com.jaba.vgl.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    String accessToken = getCookieValue(request, "AccessToken");
    String refreshToken = getCookieValue(request, "RefreshToken");

    try {
        if (accessToken != null && !jwtService.isTokenExpired(accessToken)) {
            String userEmail = jwtService.extractUserName(accessToken);
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (refreshToken != null) {
            handleTokenRefresh(refreshToken, request, response, filterChain);
            return;
        }
    }
    catch (ServletException | IOException e) {
        // This catch block can handle ExpiredJwtException and any other JWT related exceptions
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/plain");
        response.getWriter().write("Unauthorized: " + e.getMessage());
        return;
    }
    filterChain.doFilter(request, response);
}

    private void handleTokenRefresh(String refreshToken, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String username = jwtService.extractUserName(refreshToken);
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String newAccessToken = jwtService.generateAccessToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), userDetails);

                setCookie(response, "AccessToken", newAccessToken, 60 * 60); // 1 hour expiry
                setCookie(response, "RefreshToken", newRefreshToken, 60 * 60 * 24); // 24 hours expiry

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid refresh token, please re-authenticate");
            }
        }
        catch (ServletException | IOException e) {
            // This catch block can handle ExpiredJwtException and any other JWT related exceptions
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.getWriter().write("Unauthorized: " + e.getMessage());
        }
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true if using HTTPS
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath("/"); // Set the path where the cookie is available
        response.addCookie(cookie);
    }
}
