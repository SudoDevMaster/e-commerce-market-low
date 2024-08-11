package com.marketlow.products.domain.service;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import com.marketlow.products.infraestructure.mapper.IProductMapper;
import com.marketlow.products.infraestructure.repository.IProductRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Mock
    IProductRepository iProductRepository;
    @InjectMocks
    ProductService iProductService;
    @Mock
    IProductMapper iProductMapper;
    @Autowired
    IProductMapper iProductMapperAutowired;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createProduct(){
        when(iProductRepository.save(any())).thenReturn(creationInfoEntityProduct());
        when(iProductMapper.toDomain(any())).thenReturn(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        Product product = iProductService.save(any());
        Assertions.assertNotNull(product, "The products are not null");
        Assertions.assertEquals("Test",product.getName(), "The product is 'Test'");
    }

    @Test
    void updateProduct(){

    }

    @Test
    @DisplayName("Product Test - When get a product")
    void getByUuiProduct(){
        when(iProductRepository.findByUui(any())).thenReturn(creationInfoEntityProduct());
        when(iProductMapper.toDomain(any())).thenReturn(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        Product product = iProductService.getByUui(anyString());
        Assertions.assertNotNull(product, "The products are not null");
        Assertions.assertEquals("Test",product.getName(), "The product is 'Test'");
    }

    @Test
    @DisplayName("Product Test - When get a list of products")
    void getAllProducts(){
        when(iProductRepository.findAll()).thenReturn(creationListInfoEntityProduct());
        when(iProductMapper.toDomainList(any())).thenReturn(iProductMapperAutowired.toDomainList(creationListInfoEntityProduct()));
        List<Product> listProducts = iProductService.getAllProducts();
        Assertions.assertNotNull(listProducts, "The products are not null");
        Assertions.assertEquals(3,listProducts.size(), "The products are three");
    }

    @Test
    @DisplayName("Product Test - When get a list of products actives")
    void getAllActiveProducts(){
        when(iProductRepository.findByStateTrue()).thenReturn(creationListInfoEntityProduct());
        when(iProductMapper.toDomainList(any())).thenReturn(iProductMapperAutowired.toDomainList(creationListInfoEntityProduct()));
        List<Product> listProducts = iProductService.getAllActiveProducts();
        Assertions.assertNotNull(listProducts, "The products are not null");
        Assertions.assertEquals(3,listProducts.size(), "The products are three");
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
