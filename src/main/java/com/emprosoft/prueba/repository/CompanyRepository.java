package com.emprosoft.prueba.repository;


import com.emprosoft.prueba.entity.Company;
import com.emprosoft.prueba.entity.CompanyDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByCodigoCompany(String codigoCompany);

    @Query(value = """
            SELECT c.codigo_company as codigo, 
                   c.name_company as name, 
                   a.app_name as appName, 
                   v.version as version
            FROM COMPANY c
            JOIN VERSION_COMPANY vc ON c.id_company = vc.company_id
            JOIN APPLICATION a ON vc.app_id = a.app_id
            JOIN VERSION v ON a.app_id = v.app_id
            WHERE c.codigo_company = :codigoCompany
            """, nativeQuery = true)
    Optional<CompanyDetailProjection> findCompanyDetailsByCode(@Param("codigoCompany") String codigoCompany);
}
