package com.aminov.controller;

import com.aminov.model.Category;
import com.aminov.model.Product;
import com.aminov.service.CategoryService;
import com.aminov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    /**
     * Returns list of all products for client's account
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");

        List<Product> products = productService.allProducts();
        modelAndView.addObject("productsList", products);

        return modelAndView;
    }

    /**
     * Returns list of all categories for admin's account
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView allCategories() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");

        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);

        return modelAndView;
    }

    /**
     * Deletes chosen category
     */
    @RequestMapping(value = "/delete/{category}", method = RequestMethod.GET)
    public ModelAndView deleteGame(@PathVariable("category") String categoryId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories");
        Category category = categoryService.getById(categoryId);
        categoryService.delete(category);
        return modelAndView;
    }

    /**
     * Adds written category
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView();
        categoryService.add(category);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}