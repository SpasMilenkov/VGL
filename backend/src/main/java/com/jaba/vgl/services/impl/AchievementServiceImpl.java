package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.UserNotFoundException;
import com.jaba.vgl.models.dto.mapper.AchievementRequestDtoMapper;
import com.jaba.vgl.models.dto.mapper.AchievementResponseDtoMapper;
import com.jaba.vgl.models.entities.Achievement;
import com.jaba.vgl.models.entities.User;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.services.AchievementService;
import com.jaba.vgl.services.UserService;
import lombok.RequiredArgsConstructor;
import com.jaba.vgl.repositories.AchievementRepository;
import org.springframework.stereotype.Service;
import com.jaba.vgl.models.dto.AchievementRequestDto;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementResponseDtoMapper achievementResponseMapper;
    private final AchievementRequestDtoMapper achievementRequestMapper;
    private final UserRepository userRepository;

    @Override
    public Achievement createAchievement(AchievementRequestDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User with that id does not exist"));
        Achievement achievement =  achievementRequestMapper
                .mapAchievementRequestDtoToAchievement(dto, user);
        return achievementRepository.save(achievement);
    }

    @Override
    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public Achievement updateAchievement(Long id, AchievementRequestDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User with that id does not exist"));
        return achievementRepository.findById(id)
                .map(existingAchievement -> {
                    Achievement updatedAchievement = achievementRequestMapper
                            .mapAchievementRequestDtoToAchievement(dto, user);
                    updatedAchievement.setId(existingAchievement.getId()); // Ensure the ID is retained
                    return achievementRepository.save(updatedAchievement);
                })
                .orElseThrow(() -> new RuntimeException("Achievement not found with id " + id));
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }
}
