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
    public ModelAndView productsPageClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");

        List<Product> products = productService.allProducts();
        modelAndView.addObject("productsList", products);

        return modelAndView;
    }

    /**
     * Returns list of all products for administrator's account
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView productsPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productsAdmin");
        List<Product> products = productService.allProducts();
        modelAndView.addObject("productsList", products);
        return modelAndView;
    }

    @RequestMapping(value = "products/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<Category> categories = categoryService.allCategories();
        Product product = productService.getById(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("categoriesList", categories);
        return modelAndView;
    }

    @RequestMapping(value = "products/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        productService.edit(product);
        modelAndView.setViewName("redirect:/productsAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "products/add", method = RequestMethod.GET)
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);
        return modelAndView;
    }

    @RequestMapping(value = "products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("game") Product product){
        ModelAndView modelAndView = new ModelAndView();
        productService.add(product);
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    /**
     * Returns list of all categories for administrator's account
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView categoriesPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");

        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);

        return modelAndView;
    }

    /**
     * Deletes chosen category
     */
    @RequestMapping(value = "categories/delete/{category}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("category") String categoryId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories");
        Category category = categoryService.getById(categoryId);
        categoryService.delete(category);
        return modelAndView;
    }

    /**
     * Adds written category
     */
    @RequestMapping(value = "categories/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView();
        categoryService.add(category);
        modelAndView.setViewName("redirect:/categories");
        return modelAndView;
    }

}