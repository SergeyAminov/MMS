package com.aminov.mapper;

import com.aminov.dao.ProductDAO;
import com.aminov.dto.CategoryDto;
import com.aminov.model.Category;
import com.aminov.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExCategoryMapper {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    public Category toEntity(CategoryDto categoryDto){

        List<Product> productList = new ArrayList<>();
        for (int id : categoryDto.getProductIdList()){
            productList.add(this.productDAO.getById(id));
        }

        return new Category(
                categoryDto.getId(),
                categoryDto.getTitle(),
                productList
        );
    }

    public CategoryDto toDto(Category category){

        List<Integer> productIdList = new ArrayList<>();
        for (Product product : category.getProductList()){
            productIdList.add(product.getId());
        }

        return new CategoryDto(
                category.getId(),
                category.getTitle(),
                productIdList
        );
    }

}
