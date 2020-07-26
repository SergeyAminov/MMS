package com.aminov.controller;

import com.aminov.model.Category;
import com.aminov.model.Product;
import com.aminov.service.CategoryService;
import com.aminov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");

        List<Product> products = productService.allProducts();
        modelAndView.addObject("productsList", products);

        return modelAndView;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView allCategories() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");

        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);

        return modelAndView;
    }

}