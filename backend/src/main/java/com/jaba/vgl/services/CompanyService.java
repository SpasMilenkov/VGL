package com.jaba.vgl.services;

import com.jaba.vgl.models.dto.CompanyDto;

public interface CompanyService {

    CompanyDto getCompany(Long id);

    void saveCompany(CompanyDto companyDto);

    int deleteCompany(Long id);

    void updateCompany(CompanyDto companyDto);

    void truncateTable();
}
