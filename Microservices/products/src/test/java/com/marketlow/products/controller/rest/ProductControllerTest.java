package com.marketlow.products.controller.rest;

import com.marketlow.products.application.port.IProductApplicationService;
import com.marketlow.products.controller.rest.custom.ApiResponse;
import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
public class ProductControllerTest {
    @Mock
    private IProductApplicationService iProductApplicationService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Product Controller Test - When create a new product")
    void saveProduct() throws CustomException {
        Product productTest = creationInfoProduct();
        Mockito.when(iProductApplicationService.saveProduct(any(Product.class))).thenReturn(productTest);
        ResponseEntity<Object> response = productController.saveProduct(productTest);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
        assertEquals(productTest, apiResponse.getData());
    }


    @Test
    @DisplayName("Product Controller Test - When update a exist product")
    void updateProduct() throws CustomException {
        Product productTest = creationInfoProduct();
        Mockito.when(iProductApplicationService.updateProduct(any(Product.class))).thenReturn(productTest);
        ResponseEntity<Object> response = productController.updateProduct(productTest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
        assertEquals(productTest, apiResponse.getData());
    }

    @Test
    @DisplayName("Product Controller Test - When get all products")
    void findAllProducts() throws CustomException {
        Mockito.when(iProductApplicationService.findAllProducts()).thenReturn(creationListInfoProduct());
        ResponseEntity<Object> response = productController.findAllProducts();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
        assertNotNull(apiResponse.getData());
    }

    @Test
    @DisplayName("Product Controller Test - When get all active products")
    void findAllActiveProducts() throws CustomException {
        Mockito.when(iProductApplicationService.findAllActiveProducts()).thenReturn(creationListInfoProduct());
        ResponseEntity<Object> response = productController.findAllActiveProducts();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
        assertNotNull(apiResponse.getData());
    }

    @Test
    @DisplayName("Product Controller Test - When get all active products")
    void findByUuiProduct() throws CustomException {
        Mockito.when(iProductApplicationService.findByUui(anyString())).thenReturn(creationInfoProduct());
        ResponseEntity<Object> response = productController.findByUui(anyString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
        assertNotNull(apiResponse.getData());
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
