package com.marketlow.products.application.service;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.domain.port.IProductDomainService;
import com.marketlow.products.domain.service.ProductDomainService;
import com.marketlow.products.exception.custom.CustomException;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import com.marketlow.products.infraestructure.mapper.IProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProductApplicationServiceTest {

    @Mock
    IProductDomainService iProductDomainService;
    @Mock
    IProductMapper iProductMapper;
    @Autowired
    IProductMapper iProductMapperAutowired;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private ProductEntity creationInfoEntityProduct(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(new Random().nextInt());
        productEntity.setUui(UUID.randomUUID().toString());
        productEntity.setName("Test");
        productEntity.setDescription("Test Product");
        productEntity.setPrice(1000.0);
        productEntity.setStock(5);
        productEntity.setCategory("Test");
        return productEntity;
    }

    private ArrayList<ProductEntity> creationListInfoEntityProduct(){
        ArrayList listProduct = new ArrayList<ProductEntity>();
        listProduct.add(creationInfoEntityProduct());
        listProduct.add(creationInfoEntityProduct());
        listProduct.add(creationInfoEntityProduct());
        return listProduct;
    }

}
