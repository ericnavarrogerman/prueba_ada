package com.emprosoft.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDetailDTO {
    private String codigoCompany;
    private String nameCompany;
    private String appName;
    private String version;
}
