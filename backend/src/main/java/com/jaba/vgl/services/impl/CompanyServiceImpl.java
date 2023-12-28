package com.jaba.vgl.services.impl;

import com.jaba.vgl.exceptions.CompanyNotFoundException;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.mapper.CompanyDtoMapper;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.repositories.impl.CompanyRepositoryImpl;
import com.jaba.vgl.services.CompanyService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryImpl companyRepository;
    private final CompanyDtoMapper companyDtoMapper;
    private final JdbcTemplate jdbcTemplate;


    public CompanyServiceImpl(CompanyRepositoryImpl companyRepository,
                              CompanyDtoMapper companyDtoMapper,
                              JdbcTemplate jdbcTemplate) {
        this.companyRepository = companyRepository;
        this.companyDtoMapper = companyDtoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CompanyDto getCompany(Long id) {
        return companyRepository
                .findById(id)
                .map(companyDtoMapper)
                .orElseThrow(() -> new CompanyNotFoundException(
                        String.format("Company with id %d not found.",
                                id
                        )
                ));
    }

    @Override
    public void saveCompany(CompanyDto companyDto) {
        Company company = companyDto.toEntity();

        companyRepository.save(company);
    }

    @Override
    public int deleteCompany(Long id) {
        return companyRepository.deleteCompanyById(id);
    }

    @Override
    public void updateCompany(CompanyDto companyDto) {
        saveCompany(companyDto);

//        Company company = companyDto.toEntity();

//        companyRepository.updateCompany(company.getId(), company.getName(), company.getStudio(), company.getGames());
    }

    @Override
    public void truncateTable() {
        companyRepository.truncate();

        String sqlStatement = "ALTER SEQUENCE company_sequence RESTART WITH 1";
        jdbcTemplate.execute(sqlStatement);
    }
}
