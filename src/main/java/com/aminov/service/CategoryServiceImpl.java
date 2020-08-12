package com.aminov.service;

import com.aminov.dao.CategoryDAO;
import com.aminov.dto.CategoryDto;
import com.aminov.mapper.CategoryMapper;
import com.aminov.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService<CategoryDto>{

    private CategoryDAO<Category> categoryDAO;
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryDAO(CategoryDAO<Category> categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public List<CategoryDto> allItems() {
        return this.categoryDAO
                .allItems()
                .stream()
                .map(this.categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(CategoryDto categoryDto) {
        this.categoryDAO.add(this.categoryMapper.toEntity(categoryDto));
    }

    @Transactional
    @Override
    public void delete(CategoryDto categoryDto) {
        this.categoryDAO.delete(this.categoryMapper.toEntity(categoryDto));
    }

    @Transactional
    @Override
    public void edit(CategoryDto categoryDto) {
        this.categoryDAO.edit(this.categoryMapper.toEntity(categoryDto));
    }

    @Transactional
    @Override
    public CategoryDto getById(int id) {
        return this.categoryMapper.toDto(this.categoryDAO.getById(id));
    }

    @Transactional
    @Override
    public Map<Integer, String> getCategoryIdTitleMap(){
        Map<Integer, String> categoryMap = new HashMap<>();
        for (CategoryDto category : this.allItems())
            categoryMap.put(category.getId(), category.getTitle());
        return categoryMap;
    }

}
