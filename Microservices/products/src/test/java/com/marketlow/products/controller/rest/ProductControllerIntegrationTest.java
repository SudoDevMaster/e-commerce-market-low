package com.marketlow.products.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlow.products.application.port.IProductApplicationService;
import com.marketlow.products.domain.model.Product;
import com.marketlow.products.domain.port.IProductDomainService;
import com.marketlow.products.exception.custom.CustomException;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import com.marketlow.products.infraestructure.repository.IProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProductController.class)
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductRepository iProductRepository;

    @MockBean
    private IProductApplicationService iProductApplicationService;

    @MockBean
    private IProductDomainService iProductDomainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Product Integration Test - When get all products")
    void integrationGetAllProducts() throws Exception {
        when(iProductRepository.findAll()).thenReturn(creationListInfoEntityProduct());
        when(iProductDomainService.getAllProducts()).thenReturn(creationListInfoProduct());
        when(iProductApplicationService.findAllProducts()).thenReturn(creationListInfoProduct());
        mockMvc.perform(get("/marketlow/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    @DisplayName("Product Integration Test - When get all active products")
    void integrationGetAllActiveProducts() throws Exception {
        when(iProductRepository.findByStateTrue()).thenReturn(creationListInfoEntityProduct());
        when(iProductDomainService.getAllActiveProducts()).thenReturn(creationListInfoProduct());
        when(iProductApplicationService.findAllActiveProducts()).thenReturn(creationListInfoProduct());
        mockMvc.perform(get("/marketlow/products/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    @DisplayName("Product Integration Test - When get by uui product")
    void integrationGetByUuiProduct() throws Exception {
        Product product = creationInfoProduct();
        when(iProductRepository.findByUui(any())).thenReturn(Optional.of(creationInfoEntityProduct()));
        when(iProductDomainService.getByUui(any())).thenReturn(product);
        when(iProductApplicationService.findByUui(any())).thenReturn(product);
        mockMvc.perform(get("/marketlow/products/"+product.getUui()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.message", is("OK")))
                .andExpect(jsonPath("$.data.uui", is(product.getUui())))
                .andExpect(jsonPath("$.data.name", is("Test")))
                .andExpect(jsonPath("$.data.description", is("Test Product")))
                .andExpect(jsonPath("$.data.price", is(1000.0)))
                .andExpect(jsonPath("$.data.stock", is(5)))
                .andExpect(jsonPath("$.data.category", is("Test")))
                .andExpect(jsonPath("$.data.state", is(true)));
    }

    @Test
    @DisplayName("Product Integration Test - When save a product")
    void integrationSaveProduct() throws Exception {
        Product product = creationInfoProduct();
        when(iProductRepository.save(any())).thenReturn(creationInfoEntityProduct());
        when(iProductDomainService.save(any())).thenReturn(product);
        when(iProductApplicationService.saveProduct(any())).thenReturn(product);
        mockMvc.perform(post("/marketlow/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.message", is("OK")))
                .andExpect(jsonPath("$.data.uui", is(product.getUui())))
                .andExpect(jsonPath("$.data.name", is("Test")))
                .andExpect(jsonPath("$.data.description", is("Test Product")))
                .andExpect(jsonPath("$.data.price", is(1000.0)))
                .andExpect(jsonPath("$.data.stock", is(5)))
                .andExpect(jsonPath("$.data.category", is("Test")))
                .andExpect(jsonPath("$.data.state", is(true)));
    }

    @Test
    @DisplayName("Product Integration Test - When update a product")
    void integrationUpdateProduct() throws Exception {
        Product product = creationInfoProduct();
        when(iProductRepository.save(any())).thenReturn(creationInfoEntityProduct());
        when(iProductDomainService.update(any())).thenReturn(product);
        when(iProductApplicationService.updateProduct(any())).thenReturn(product);
        mockMvc.perform(put("/marketlow/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.message", is("OK")))
                .andExpect(jsonPath("$.data.uui", is(product.getUui())))
                .andExpect(jsonPath("$.data.name", is("Test")))
                .andExpect(jsonPath("$.data.description", is("Test Product")))
                .andExpect(jsonPath("$.data.price", is(1000.0)))
                .andExpect(jsonPath("$.data.stock", is(5)))
                .andExpect(jsonPath("$.data.category", is("Test")))
                .andExpect(jsonPath("$.data.state", is(true)));
    }

    @Test
    @DisplayName("Product Integration Test - When save a product an occurred custom exception")
    void integrationSaveProductCustomException() throws Exception {
        Product invalidProduct = creationInfoProduct();
        when(iProductRepository.save(any(ProductEntity.class)))
                .thenThrow(CustomException.builder().message("Product name cannot be null").build());
        when(iProductApplicationService.saveProduct(any(Product.class)))
                .thenThrow(CustomException.builder().message("Product name cannot be null").build());
        mockMvc.perform(post("/marketlow/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProduct)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomException))
                .andExpect(result -> assertEquals("Product name cannot be null", result.getResolvedException().getMessage()));
    }


    @Test
    @DisplayName("Product Integration Test - When save a product an occurred custom exception")
    void integrationUpdateProductException() throws Exception {
        Product invalidProduct = creationInfoProduct();
        when(iProductRepository.findByUui(any()))
                .thenThrow(new RuntimeException("An unexpected error occurred exception"));
        mockMvc.perform(put("/marketlow/products/{}","Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProduct)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertEquals("Request method 'PUT' is not supported", result.getResolvedException().getMessage()));
    }

    private Product creationInfoProduct(){
        return Product.builder()
                .uui(UUID.randomUUID().toString())
                .name("Test")
                .description("Test Product")
                .price(1000.0)
                .stock(5)
                .category("Test")
                .state(Boolean.TRUE)
                .build();
    }

    private ArrayList<Product> creationListInfoProduct(){
        ArrayList listProduct = new ArrayList<Product>();
        listProduct.add(creationInfoProduct());
        listProduct.add(creationInfoProduct());
        listProduct.add(creationInfoProduct());
        return listProduct;
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
