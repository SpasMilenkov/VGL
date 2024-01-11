package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.GameRepository;
import com.jaba.vgl.repositories.custom.GameRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(
        rollbackFor = Exception.class,
        propagation = Propagation.REQUIRED
)
public interface GameRepositoryImpl extends GameRepository, GameRepositoryCustom {

    @Override
    @Query("SELECT g FROM Game g WHERE g.id = ?1")
    Optional<Game> findGameById(Long id);

    @Override
    @Modifying
    @Query("DELETE FROM Game g WHERE g.id = ?1")
    int deleteGameById(Long id);

    @Query("SELECT g FROM Game g WHERE g.id IN :ids")
    List<Game> findByGameIds(List<Long> ids);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE game_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
