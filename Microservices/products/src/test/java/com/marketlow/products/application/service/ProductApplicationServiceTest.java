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
    @InjectMocks
    ProductApplicationService productApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Product Application Test - When create a new product in use case")
    void createProduct() throws CustomException{
        when(iProductDomainService.save(any())).thenReturn(creationInfoProduct());
        Product product = productApplicationService.saveProduct(creationInfoProduct());
        assertNotNull(product);
        verify(iProductDomainService).save(any());
    }

    @Test
    @DisplayName("Product Application Test - When update exist product in use case")
    void updateProduct() throws CustomException{
        when(iProductDomainService.update(any())).thenReturn(creationInfoProduct());
        Product product = productApplicationService.updateProduct(creationInfoProduct());
        assertNotNull(product);
        verify(iProductDomainService).update(any());
    }

    @Test
    @DisplayName("Product Application Test - When get a product for it's uui code")
    void findByUuiProduct() throws CustomException{
        when(iProductDomainService.getByUui(any())).thenReturn(creationInfoProduct());
        Product product = productApplicationService.findByUui(anyString());
        assertNotNull(product);
        verify(iProductDomainService).getByUui(any());
    }

    @Test
    @DisplayName("Product Application Test - When get a list of products")
    void findAllProducts() throws CustomException{
        when(iProductDomainService.getAllProducts()).thenReturn(creationListInfoProduct());
        List<Product> listProduct = productApplicationService.findAllProducts();
        assertNotNull(listProduct);
        assertEquals(3, listProduct.size(), "It have a three products in data");
        verify(iProductDomainService).getAllProducts();
    }

    @Test
    @DisplayName("Product Application Test - When get a list of products actives")
    void findAllActiveProducts() throws CustomException{
        when(iProductDomainService.getAllActiveProducts()).thenReturn(creationListInfoProduct());
        List<Product> listProduct = productApplicationService.findAllActiveProducts();
        assertNotNull(listProduct);
        assertEquals(3, listProduct.size(), "It have a three products in data");
        verify(iProductDomainService).getAllActiveProducts();
    }


    private Product creationInfoProduct(){
        return Product.builder()
                .uui(UUID.randomUUID().toString())
                .name("Test")
                .description("Test Product")
                .price(1000.0)
                .stock(5)
                .category("Test")
                .build();
    }

    private ArrayList<Product> creationListInfoProduct(){
        ArrayList listProduct = new ArrayList<Product>();
        listProduct.add(creationInfoProduct());
        listProduct.add(creationInfoProduct());
        listProduct.add(creationInfoProduct());
        return listProduct;
    }

}
