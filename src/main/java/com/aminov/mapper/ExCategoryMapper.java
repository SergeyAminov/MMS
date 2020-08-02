package com.aminov.mapper;

import com.aminov.dto.CategoryDto;
import com.aminov.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExCategoryMapper {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Category toEntity(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto toDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

}
