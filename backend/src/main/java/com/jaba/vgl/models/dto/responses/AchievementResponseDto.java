package com.jaba.vgl.models.dto.responses;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public record AchievementResponseDto(
        String userName,
        String gameTitle,
        String title,
        String description,
        String difficulty
) {
}
