package com.emprosoft.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VERSION_COMPANY")
@Data
public class VersionCompany {
    @Id
    @Column(name = "version_company_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Application application;

    @Column(name = "version_company_description")
    private String description;
}