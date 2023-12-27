package com.jaba.vgl.repositories.custom;

import com.jaba.vgl.models.entities.Game;

import java.util.List;

public interface CompanyRepositoryCustom {

    void truncate();

    int deleteCompanyById(Long id);

    void updateCompany(Long id, String name, String studio, List<Game> games);
}
