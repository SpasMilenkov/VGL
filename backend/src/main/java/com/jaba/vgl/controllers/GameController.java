package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameWithCompanyDto> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGame(id));
    }

    @GetMapping("/entity")
    public ResponseEntity<Game> getGameEntity(@RequestParam String name, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(gameService.getGameEntity(name, companyDto));
    }

    @GetMapping
    public ResponseEntity<GameWithCompanyDto> getGame(@RequestParam String name, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(gameService.getGame(name, companyDto));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Long>> getGameId(@RequestParam String name, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(gameService.getGameId(name, companyDto));
    }

    @PutMapping
    public ResponseEntity<Void> updateGame(@RequestBody GameWithCompanyDto game) {
        gameService.updateGame(game);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody GameWithCompanyDto game) {
        gameService.createGame(game);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.deleteGame(id));
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteGame(@RequestParam String name, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(gameService.deleteGame(name, companyDto));
    }

    @PostMapping("/truncate")
    public ResponseEntity<Void> truncateTable() {
        gameService.truncateTable();
        return ResponseEntity.ok().build();
    }
}