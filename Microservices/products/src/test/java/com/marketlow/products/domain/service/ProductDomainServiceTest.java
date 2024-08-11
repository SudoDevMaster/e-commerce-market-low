package com.marketlow.products.domain.service;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProductDomainServiceTest {

    @Mock
    IProductRepository iProductRepository;
    @InjectMocks
    ProductDomainService iProductDomainService;
    @Mock
    IProductMapper iProductMapper;
    @Autowired
    IProductMapper iProductMapperAutowired;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Product Test - When create a product")
    void saveProduct() throws CustomException {
        when(iProductMapper.toEntity(any())).thenReturn(creationInfoEntityProduct());
        when(iProductRepository.save(any())).thenReturn(creationInfoEntityProduct());
        when(iProductMapper.toDomain(any())).thenReturn(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        Product savedProduct = iProductDomainService.save(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        assertNotNull(savedProduct);
        verify(iProductMapper).toEntity(any());
        verify(iProductRepository).save(any());
        verify(iProductMapper).toDomain(any());
    }

    @Test
    void updateProduct() throws CustomException {
        when(iProductRepository.findByUui(any())).thenReturn(Optional.of(creationInfoEntityProduct()));
        when(iProductMapper.toEntity(any())).thenReturn(creationInfoEntityProduct());
        when(iProductRepository.save(any())).thenReturn(creationInfoEntityProduct());
        when(iProductMapper.toDomain(any())).thenReturn(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        Product updatedProduct = iProductDomainService.update(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        assertNotNull(updatedProduct, "The products are not null");
        verify(iProductRepository).findByUui(any());
        verify(iProductRepository).save(any());
    }

    @Test
    @DisplayName("Product Test - When get a product")
    void getByUuiProduct() throws CustomException {
        when(iProductRepository.findByUui(any())).thenReturn(Optional.of(creationInfoEntityProduct()));
        when(iProductMapper.toDomain(any())).thenReturn(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        Product product = iProductDomainService.getByUui(anyString());
        assertNotNull(product, "The products are not null");
        Assertions.assertEquals("Test",product.getName(), "The product is 'Test'");
        verify(iProductRepository).findByUui(any());
    }

    @Test
    @DisplayName("Product Test - When get a list of products")
    void getAllProducts() throws CustomException {
        when(iProductRepository.findAll()).thenReturn(creationListInfoEntityProduct());
        when(iProductMapper.toDomainList(any())).thenReturn(iProductMapperAutowired.toDomainList(creationListInfoEntityProduct()));
        List<Product> listProducts = iProductDomainService.getAllProducts();
        assertNotNull(listProducts, "The products are not null");
        Assertions.assertEquals(3,listProducts.size(), "The products are three");
        verify(iProductRepository).findAll();
    }

    @Test
    @DisplayName("Product Test - When get a list of products actives")
    void getAllActiveProducts() throws CustomException {
        when(iProductRepository.findByStateTrue()).thenReturn(creationListInfoEntityProduct());
        when(iProductMapper.toDomainList(any())).thenReturn(iProductMapperAutowired.toDomainList(creationListInfoEntityProduct()));
        List<Product> listProducts = iProductDomainService.getAllActiveProducts();
        assertNotNull(listProducts, "The products are not null");
        Assertions.assertEquals(3,listProducts.size(), "The products are three");
        verify(iProductRepository).findByStateTrue();
    }

    @Test
    @DisplayName("Product Test - When save product failure")
    void saveProduct_Failure() {
        when(iProductMapper.toEntity(any())).thenReturn(creationInfoEntityProduct());
        when(iProductRepository.save(any())).thenThrow(CustomException.builder().message("Failed to save product").build());
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.save(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        });
        assertEquals("Failed to save product", exception.getMessage());
    }

    @Test
    @DisplayName("Product Test - When product exist in BD")
    void saveProduct_WhenFoundProduct() {
        ProductEntity productEntity = creationInfoEntityProduct();
        Product product = iProductMapperAutowired.toDomain(productEntity);
        when(iProductRepository.findByName(any())).thenReturn(Optional.of(productEntity));
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.save(iProductMapperAutowired.toDomain(creationInfoEntityProduct()));
        });
        assertEquals("Product with name '" + product.getName() + "' already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Product Test - When update product failure")
    void updateProduct_Failure() {
        Product product = iProductMapperAutowired.toDomain(creationInfoEntityProduct());
        when(iProductMapper.toEntity(any())).thenReturn(creationInfoEntityProduct());
        when(iProductRepository.save(any())).thenThrow(CustomException.builder().message("Product not found with id " + product.getUui()).build());
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.update(product);
        });
        assertEquals("Product not found with id " + product.getUui(), exception.getMessage());
    }

    @Test
    @DisplayName("Product Test - When products not found")
    void getAllProducts_NoProductsFound() {
        when(iProductRepository.findAll()).thenReturn(new ArrayList<>());
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.getAllProducts();
        });
        assertEquals("No products found", exception.getMessage());
    }

    @Test
    @DisplayName("Product Test - When products active not found")
    void getAllActiveProducts_NoProductsFound() {
        when(iProductRepository.findByStateTrue()).thenReturn(new ArrayList<>());
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.getAllActiveProducts();
        });
        assertEquals("No active products found", exception.getMessage());
    }

    @Test
    @DisplayName("Product Test - When product not found")
    void getProducts_NoProductFound() {
        Product product = iProductMapperAutowired.toDomain(creationInfoEntityProduct());
        when(iProductRepository.findByUui(product.getUui())).thenReturn(Optional.of(new ProductEntity()));
        CustomException exception = assertThrows(CustomException.class, () -> {
            iProductDomainService.getByUui(product.getUui());
        });
        assertEquals("Product not found with UUID: " + product.getUui() , exception.getMessage());
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
