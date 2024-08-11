package com.marketlow.products.application.port;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;

import java.util.List;

public interface IProductApplicationService {
    List<Product> findAllProducts() throws CustomException;
    List<Product> findAllActiveProducts() throws CustomException;
    Product findByUui(String s) throws CustomException;
    Product saveProduct(Product product) throws CustomException;
    Product updateProduct(Product product) throws CustomException;
}
