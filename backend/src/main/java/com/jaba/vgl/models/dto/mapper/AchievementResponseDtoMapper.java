package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.responses.AchievementResponseDto;
import com.jaba.vgl.models.entities.Achievement;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AchievementResponseDtoMapper implements Function<Achievement, AchievementResponseDto> {

    @Override
    public AchievementResponseDto apply(Achievement achievement) {
        String userName = achievement.getUser().getUsername();
        String gameTitle = achievement.getGame().getName();

        // Now create the DTO with the data retrieved from the Achievement entity and its related entities.
        return new AchievementResponseDto(
                achievement.getId(),
                userName,
                gameTitle,
                achievement.getTitle(),
                achievement.getDescription(),
                achievement.getDifficulty().name()
        );
    }
}