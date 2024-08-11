package com.marketlow.products.infraestructure.mapper;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    Product toDomain(ProductEntity entity);
    ProductEntity toEntity(Product product);
    List<Product> toDomainList(List<ProductEntity> entities);
    List<ProductEntity> toEntityList(List<Product> products);
}