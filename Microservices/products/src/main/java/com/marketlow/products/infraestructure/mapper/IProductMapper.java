package com.marketlow.products.infraestructure.mapper;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.infraestructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    //IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    void updateProductFromEntity(ProductEntity dto, @MappingTarget ProductEntity entity);

    Product toDomain(ProductEntity entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ProductEntity toEntity(Product product);
    List<Product> toDomainList(List<ProductEntity> entities);
}