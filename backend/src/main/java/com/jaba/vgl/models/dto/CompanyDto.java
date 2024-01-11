package com.jaba.vgl.models.dto;

import com.jaba.vgl.models.entities.Company;

import java.util.List;

public record CompanyDto(
        Long id,
        String name,
        String studio
) {
    public Company toEntity() {
        Company company = new Company();
        CompanyDto dto = this;

        company.setId(id);
        company.setName(dto.name);
        company.setStudio(dto.studio);

        return company;
    }
}
