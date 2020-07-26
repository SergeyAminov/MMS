package com.aminov.service;

import com.aminov.dao.CategoryDAO;
import com.aminov.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDAO categoryDAO;

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Transactional
    @Override
    public List<Category> allCategories() {
        return this.categoryDAO.allCategories();
    }

    @Transactional
    @Override
    public void add(Category category) {
        this.categoryDAO.add(category);
    }

    @Transactional
    @Override
    public void delete(Category category) {
        this.categoryDAO.delete(category);
    }

    @Transactional
    @Override
    public void edit(Category category) {
        this.categoryDAO.edit(category);
    }

    @Transactional
    @Override
    public Category getById(String category) {
        return this.categoryDAO.getById(category);
    }
}
