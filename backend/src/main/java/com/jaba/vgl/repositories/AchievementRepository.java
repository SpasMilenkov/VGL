package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
