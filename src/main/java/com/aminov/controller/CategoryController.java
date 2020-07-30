package com.aminov.controller;

import com.aminov.model.Category;
import com.aminov.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView categoriesPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");

        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);

        return modelAndView;
    }

    @RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories");
        Category category = categoryService.getById(id);
        categoryService.delete(category);
        return modelAndView;
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView();
        categoryService.add(category);
        modelAndView.setViewName("redirect:/categories");
        return modelAndView;
    }

    @RequestMapping(value = "/categories/edit", method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView();
        categoryService.edit(category);
        modelAndView.setViewName("redirect:/categories");
        return modelAndView;
    }

}
