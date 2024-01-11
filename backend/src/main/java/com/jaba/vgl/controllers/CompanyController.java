package com.jaba.vgl.controllers;

import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveCompany(@RequestBody CompanyDto companyDto) {
        companyService.saveCompany(companyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @PutMapping
    public ResponseEntity<Void> updateCompany(@RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/truncate")
    public ResponseEntity<Void> truncateTable() {
        companyService.truncateTable();
        return ResponseEntity.ok().build();
    }
}