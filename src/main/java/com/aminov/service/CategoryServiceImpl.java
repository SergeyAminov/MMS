package com.aminov.service;

import com.aminov.dao.CategoryDAO;
import com.aminov.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService<Category>{

    private CategoryDAO<Category> categoryDAO;

    @Autowired
    public void setCategoryDAO(CategoryDAO<Category> categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Transactional
    @Override
    public List<Category> allItems() {
        return this.categoryDAO.allItems();
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
    public Category getById(int id) {
        return this.categoryDAO.getById(id);
    }
}
