package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.models.Difficulty;
import com.jaba.vgl.models.dto.AchievementRequestDto;
import com.jaba.vgl.models.entities.Achievement;
import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.models.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AchievementRequestDtoMapper {

    public Achievement mapAchievementRequestDtoToAchievement(AchievementRequestDto dto, User user) {
        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setTitle(dto.title());
        achievement.setDescription(dto.Description());
        achievement.setDifficulty(Difficulty.valueOf(dto.difficulty().toUpperCase()));

        // Handle the image
        MultipartFile image = dto.image();
        if (image != null && !image.isEmpty()) {
            try {
                achievement.setImageData(image.getBytes());
            } catch (IOException e) {
                throw new GameNotFoundException("");
            }
        }

        return achievement;
    }
}