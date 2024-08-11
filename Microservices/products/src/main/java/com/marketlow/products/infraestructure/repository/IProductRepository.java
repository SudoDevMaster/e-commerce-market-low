package com.marketlow.products.infraestructure.repository;

import com.marketlow.products.infraestructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Integer> {
    List<ProductEntity> findByStateTrue();
    List<ProductEntity> findAll();
    ProductEntity findByUui(String uui);
}
