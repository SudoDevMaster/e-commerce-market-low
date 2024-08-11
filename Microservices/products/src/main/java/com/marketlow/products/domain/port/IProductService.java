package com.marketlow.products.domain.port;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.infraestructure.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    List<Product> getAllActiveProducts();

    Product getByUui(String s);
}
