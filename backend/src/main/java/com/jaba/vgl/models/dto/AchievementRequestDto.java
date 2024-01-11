package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.Difficulty;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record AchievementRequestDto(
        Long gameId,
        Long userId,
        String Description,
        String difficulty,
        String title,
        @Nullable
        MultipartFile image
) {
}
