package com.mds.stocks.service;

import com.mds.stocks.advice.exception.CompanyNotFoundException;
import com.mds.stocks.entity.Company;
import com.mds.stocks.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }



    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company updatedCompany) {
        Company existingCompany = getCompanyById(updatedCompany.getId());
        existingCompany.setName(updatedCompany.getName());
        existingCompany.setSymbol(updatedCompany.getSymbol());
        existingCompany.setFoundationDate(updatedCompany.getFoundationDate());
        return companyRepository.save(existingCompany);
    }
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
    }

    public void deleteCompany(Long id) {
        Company existingCompany=companyRepository.findById(id).orElseThrow(()-> new CompanyNotFoundException("Company not found!"));
        companyRepository.deleteById(id);
    }
}
