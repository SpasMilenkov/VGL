package com.jaba.vgl.repositories.impl;

import com.jaba.vgl.models.entities.Game;
import com.jaba.vgl.repositories.CompanyRepository;
import com.jaba.vgl.repositories.ReviewRepository;
import com.jaba.vgl.repositories.custom.CompanyRepositoryCustom;
import com.jaba.vgl.repositories.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(
        rollbackFor = Exception.class,
        propagation = Propagation.REQUIRED
)
public interface CompanyRepositoryImpl extends CompanyRepository, CompanyRepositoryCustom {

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET " +
            "c.name = :name, " +
            "c.studio = :studio, " +
            "c.games = :games " +
            "WHERE c.id = :id")
    @Override
    void updateCompany(Long id, String name, String studio, List<Game> games);

    @Override
    @Modifying
    @Query("DELETE FROM Company c WHERE c.id = ?1")
    int deleteCompanyById(Long id);

    @Override
    @Modifying
    @Query(value = "TRUNCATE TABLE company_table RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncate();
}
