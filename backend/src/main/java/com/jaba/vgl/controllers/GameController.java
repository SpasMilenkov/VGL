package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameService.saveGame(game));
    }
    @GetMapping("/random")
    public ResponseEntity<List<GameDto>> getGamesByIds() {
        return ResponseEntity.ok(gameService.getGamesByIds());
    }
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
