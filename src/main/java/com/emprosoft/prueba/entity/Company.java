package com.emprosoft.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COMPANY")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Integer id;

    @Column(name = "codigo_company")
    private String codigoCompany;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "description_company")
    private String descriptionCompany;
}
