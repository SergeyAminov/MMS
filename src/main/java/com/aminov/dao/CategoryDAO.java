package com.aminov.dao;

import com.aminov.model.Category;
import java.util.List;

public interface CategoryDAO {
    List<Category> allCategories();
    void add(Category category);
    void delete(Category category);
    void edit(Category category);
    Category getById(int id);
}
