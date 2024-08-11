package com.marketlow.products.domain.service;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.domain.port.IProductService;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import com.marketlow.products.infraestructure.mapper.IProductMapper;
import com.marketlow.products.infraestructure.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private IProductRepository iProductRepository;
    private IProductMapper iProductMapper;
    @Override
    public List<Product> getAllProducts() {
        return iProductMapper.toDomainList(iProductRepository.findAll());
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return iProductMapper.toDomainList(iProductRepository.findByStateTrue());
    }

    @Override
    public Product getByUui(String uui) {
        return iProductMapper.toDomain(iProductRepository.findByUui(uui));
    }
}
