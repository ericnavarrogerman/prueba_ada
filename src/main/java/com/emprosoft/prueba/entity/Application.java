package com.emprosoft.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "APPLICATION")
@Data
public class Application {
    @Id
    @Column(name = "app_id")
    private Integer id;

    @Column(name = "app_code")
    private String code;

    @Column(name = "app_name")
    private String name;

    @Column(name = "app_description")
    private String description;
}