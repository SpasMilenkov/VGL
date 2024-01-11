package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.GameDescriptionDto;
import com.jaba.vgl.models.dto.NewsItemDto;
import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.dto.PlayerSummaryDto;
import com.jaba.vgl.services.UserService;
import com.jaba.vgl.services.clients.SteamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/steam/")
@RequiredArgsConstructor
public class SteamController {
    private final SteamService steamService;
    private final UserService userService;

    @GetMapping("player-summary")
    public ResponseEntity<PlayerSummaryDto> getUserSummary(@RequestParam String email){
        PlayerSummaryDto result = steamService.getPlayerSummary(email);

        return ResponseEntity.ok(result);
    }
    @GetMapping("game-details")
    public ResponseEntity<GameDescriptionDto> getGameDetails(@RequestParam String gameId){
        GameDescriptionDto gameDescription = steamService.getGameDescription(gameId);

        return ResponseEntity.ok(gameDescription);
    }
    @GetMapping("game-news")
    public ResponseEntity<List<NewsItemDto>> getGameNews(@RequestParam String steamId){
        List<NewsItemDto> gameNews = steamService.getGameNews(steamId);

        return ResponseEntity.ok(gameNews);
    }
    @GetMapping("get-owned-games")
    public ResponseEntity<List<OwnedGameDto>> getOwnedGames(@RequestParam String steamId){
        List<OwnedGameDto> ownedGames = steamService.getOwnedGames(steamId);

        return ResponseEntity.ok(ownedGames);
    }
}
