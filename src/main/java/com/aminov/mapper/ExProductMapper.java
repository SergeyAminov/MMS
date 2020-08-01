package com.aminov.mapper;

import com.aminov.dao.CategoryDAO;
import com.aminov.dao.ParametersDAO;
import com.aminov.dto.ProductDto;
import com.aminov.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExProductMapper {

    private ParametersDAO parametersDAO;
    private CategoryDAO categoryDAO;

    @Autowired
    public void setProductDAO(ParametersDAO parametersDAO){
        this.parametersDAO = parametersDAO;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    public Product toEntity(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice(),
                this.parametersDAO.getById(productDto.getParametersId()),
                this.categoryDAO.getById(productDto.getCategoryId()),
                productDto.getCount()
        );
    }

    public ProductDto toDto(Product product){
        return new ProductDto(
              product.getId(),
              product.getTitle(),
              product.getPrice(),
              product.getCount(),
              product.getCategory().getId(),
              product.getParameters().getId()
        );
    }

}
