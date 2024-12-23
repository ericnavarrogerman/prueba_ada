package com.emprosoft.prueba.service;

import com.emprosoft.prueba.dto.CompanyDetailDTO;
import com.emprosoft.prueba.entity.Company;
import com.emprosoft.prueba.entity.CompanyDetailProjection;
import com.emprosoft.prueba.exception.DuplicateResourceException;
import com.emprosoft.prueba.exception.ResourceNotFoundException;
import com.emprosoft.prueba.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la compañía con ID: " + id));
    }

    public Company createCompany(Company company) {
        if (companyRepository.findByCodigoCompany(company.getCodigoCompany()).isPresent()) {
            throw new DuplicateResourceException(
                    "Ya existe una compañía con el código: " + company.getCodigoCompany());
        }
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company company) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la compañía con ID: " + id);
        }

        Optional<Company> existingCompanyWithCode = companyRepository
                .findByCodigoCompany(company.getCodigoCompany());

        if (existingCompanyWithCode.isPresent() &&
                !existingCompanyWithCode.get().getId().equals(id)) {
            throw new DuplicateResourceException(
                    "Ya existe otra compañía con el código: " + company.getCodigoCompany());
        }

        company.setId(id);
        return companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la compañía con ID: " + id);
        }
        companyRepository.deleteById(id);
    }

    public CompanyDetailDTO getCompanyDetails(String codigoCompany) {
        CompanyDetailProjection projection =   companyRepository.findCompanyDetailsByCode(codigoCompany)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontraron detalles para la compañía con código: " + codigoCompany));

        return new CompanyDetailDTO(
                projection.getCodigo(),
                projection.getName(),
                projection.getAppName(),
                projection.getVersion()
        );
    }
}

