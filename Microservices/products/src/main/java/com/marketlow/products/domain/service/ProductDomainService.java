package com.marketlow.products.domain.service;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.domain.port.IProductDomainService;
import com.marketlow.products.exception.custom.CustomException;
import com.marketlow.products.infraestructure.mapper.IProductMapper;
import com.marketlow.products.infraestructure.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDomainService implements IProductDomainService {
    private IProductRepository iProductRepository;
    private IProductMapper iProductMapper;
    @Override
    public List<Product> getAllProducts() throws CustomException {
        return Optional.of(iProductRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(iProductMapper::toDomainList)
                .orElseThrow(() -> CustomException.builder().message("No products found").build());
    }
    @Override
    public List<Product> getAllActiveProducts() throws CustomException {
        return Optional.of(iProductRepository.findByStateTrue())
                .filter(list -> !list.isEmpty())
                .map(iProductMapper::toDomainList)
                .orElseThrow(() -> CustomException.builder().message("No active products found").build());
    }
    @Override
    public Product getByUui(String uui) throws CustomException {
        return iProductRepository.findByUui(uui)
                .map(iProductMapper::toDomain)
                .orElseThrow(() -> CustomException.builder().message("Product not found with UUID: " + uui).build());
    }
    @Override
    public Product save(Product product) throws CustomException {
        if (iProductRepository.findByName(product.getName()).isPresent()) {
            throw CustomException.builder().message("Product with name '" + product.getName() + "' already exists").build();
        }
        return Optional.of(product)
                .map(iProductMapper::toEntity)
                .map(iProductRepository::save)
                .map(iProductMapper::toDomain)
                .orElseThrow(() -> CustomException.builder().message("Failed to save product").build());
    }
    @Override
    @Transactional
    public Product update(Product product) throws CustomException {
        return iProductRepository.findByUui(product.getUui())
                .map(productEntity -> {
                    iProductMapper.updateProductFromEntity(productEntity, iProductMapper.toEntity(product));
                    return iProductMapper.toDomain(iProductRepository.save(productEntity));
                })
                .orElseThrow(() -> CustomException.builder().message("Product not found with id " + product.getUui()).build());
    }
}
