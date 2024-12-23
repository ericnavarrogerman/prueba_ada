package com.emprosoft.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VERSION")
@Data
public class Version {
    @Id
    @Column(name = "version_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Application application;

    @Column(name = "version")
    private String version;

    @Column(name = "version_description")
    private String description;
}