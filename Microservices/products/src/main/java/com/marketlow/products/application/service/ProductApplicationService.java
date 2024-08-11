package com.marketlow.products.application.service;

import com.marketlow.products.application.port.IProductApplicationService;
import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;

import java.util.List;

public class ProductApplicationService implements IProductApplicationService {
    @Override
    public List<Product> findAllProducts() throws CustomException {
        return null;
    }

    @Override
    public List<Product> findAllActiveProducts() throws CustomException {
        return null;
    }

    @Override
    public Product findByUui(String s) throws CustomException {
        return null;
    }

    @Override
    public Product saveProduct(Product product) throws CustomException {
        return null;
    }

    @Override
    public Product updateProduct(Product product) throws CustomException {
        return null;
    }
}
