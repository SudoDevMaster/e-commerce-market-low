package com.marketlow.products.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uui;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private Boolean state;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", updatable = false)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
        updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }
}
