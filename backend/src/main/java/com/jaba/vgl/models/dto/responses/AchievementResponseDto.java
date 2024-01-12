package com.jaba.vgl.models.dto.responses;

public record AchievementResponseDto(
        Long id,
        String userName,
        String gameTitle,
        String title,
        String description,
        String difficulty
) {
}
