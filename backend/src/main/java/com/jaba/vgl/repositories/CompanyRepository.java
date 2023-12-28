package com.jaba.vgl.repositories;

import com.jaba.vgl.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // You can add custom query methods here if needed
}