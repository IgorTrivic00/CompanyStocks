package com.mds.stocks.controller;

import com.mds.stocks.advice.exception.BadRequestException;
import com.mds.stocks.entity.Company;
import com.mds.stocks.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;
    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> findAllCompanies() {
        return companyService.findAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        logger.debug("======== GET COMPANY BY ID ========");
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Company createCompany(@Valid  @RequestBody Company company) {
        logger.debug("======== CREATE COMPANY ========");
        if (company.getName() == null || company.getName().trim().isEmpty()) {
            throw new BadRequestException("Company name is required and cannot be blank.");
        }

        if (company.getSymbol() == null || company.getSymbol().trim().isEmpty()) {
            throw new BadRequestException("Company symbol is required and cannot be blank.");
        }
        return companyService.createCompany(company);
    }

    @PutMapping()
    public Company updateCompany(@RequestBody Company company) {
        logger.debug("======== UPDATE COMPANY ========");
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        logger.debug("======== DELETE COMPANY BY COMPANY ID ========");
        companyService.deleteCompany(id);
    }
}
