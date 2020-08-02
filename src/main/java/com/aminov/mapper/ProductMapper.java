package com.aminov.mapper;

import com.aminov.dto.ProductDto;
import com.aminov.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Product toEntity(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }

    public ProductDto toDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

}
