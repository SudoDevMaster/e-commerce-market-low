package com.marketlow.products.application.service;

import com.marketlow.products.application.port.IProductApplicationService;
import com.marketlow.products.domain.model.Product;
import com.marketlow.products.domain.port.IProductDomainService;
import com.marketlow.products.exception.custom.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductApplicationService implements IProductApplicationService {
    private IProductDomainService iProductDomainService;
    @Override
    public List<Product> findAllProducts() throws CustomException {
        return iProductDomainService.getAllProducts();
    }

    @Override
    public List<Product> findAllActiveProducts() throws CustomException {
        return iProductDomainService.getAllActiveProducts();
    }

    @Override
    public Product findByUui(String uui) throws CustomException {
        return iProductDomainService.getByUui(uui);
    }

    @Override
    public Product saveProduct(Product product) throws CustomException {
        return iProductDomainService.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws CustomException {
        return iProductDomainService.update(product);
    }
}
