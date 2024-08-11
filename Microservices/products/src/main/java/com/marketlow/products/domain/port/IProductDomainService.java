package com.marketlow.products.domain.port;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;

import java.util.List;

public interface IProductDomainService {
    List<Product> getAllProducts() throws CustomException;
    List<Product> getAllActiveProducts() throws CustomException;
    Product getByUui(String s) throws CustomException;
    Product save(Product product) throws CustomException;
    Product update(Product product) throws CustomException;
}
