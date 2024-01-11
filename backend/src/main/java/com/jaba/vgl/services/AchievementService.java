package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.AchievementRequestDto;
import com.jaba.vgl.models.entities.Achievement;

import java.util.List;
import java.util.Optional;

public interface AchievementService {
    Achievement createAchievement(AchievementRequestDto dto);
    Optional<Achievement> getAchievementById(Long id);
    List<Achievement> getAllAchievements();
    Achievement updateAchievement(Long id, AchievementRequestDto dto);
    void deleteAchievement(Long id);
}
