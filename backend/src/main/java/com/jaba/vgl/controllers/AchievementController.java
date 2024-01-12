package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.AchievementRequestDto;
import com.jaba.vgl.models.dto.responses.AchievementResponseDto;
import com.jaba.vgl.models.entities.Achievement;
import com.jaba.vgl.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping("/create")
    public ResponseEntity<Achievement> createAchievement(@ModelAttribute AchievementRequestDto achievement) {
        return ResponseEntity.ok(achievementService.createAchievement(achievement));
    }

    //Get achievements for specific game
    @GetMapping("/all")
    public ResponseEntity<List<AchievementResponseDto>> getAllAchievements() {
        var result = achievementService.getAllAchievements();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{achievementId}/image")
    public ResponseEntity<byte[]> getAchievementImage(@PathVariable Long achievementId) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(achievementService.getAchievementImage(achievementId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable Long id, @RequestBody AchievementRequestDto achievement) {
        return ResponseEntity.ok(achievementService.updateAchievement(id, achievement));
    }

    @DeleteMapping("/{id}")
    public void deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
    }
}
