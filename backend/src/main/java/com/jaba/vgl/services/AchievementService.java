package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.AchievementRequestDto;
import com.jaba.vgl.models.dto.responses.AchievementResponseDto;
import com.jaba.vgl.models.entities.Achievement;

import java.util.List;
import java.util.Optional;

public interface AchievementService {
    Achievement createAchievement(AchievementRequestDto dto);
    byte[] getAchievementImage(Long achievementId);
    Optional<Achievement> getAchievementById(Long id);
    List<AchievementResponseDto> getAllAchievements();
    Achievement updateAchievement(Long id, AchievementRequestDto dto);
    void deleteAchievement(Long id);
}
