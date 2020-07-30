package com.aminov.controller;

import com.aminov.dto.ProductDto;
import com.aminov.model.Category;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView productsPageClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");

        List<ProductDto> productDtoList = productService.allProducts();
        modelAndView.addObject("productsList", productDtoList);

        return modelAndView;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView productsPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productsAdmin");
        List<ProductDto> productDtoList = productService.allProducts();
        modelAndView.addObject("productsList", productDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/products/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<Category> categories = categoryService.allCategories();
        ProductDto productDto = productService.getById(id);
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("categoriesList", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/products/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") ProductDto productDto) {
        ModelAndView modelAndView = new ModelAndView();
        productService.edit(productDto);
        modelAndView.setViewName("redirect:/productsAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<Category> categories = categoryService.allCategories();
        modelAndView.addObject("categoriesList", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") ProductDto productDto){
        ModelAndView modelAndView = new ModelAndView();
        productService.add(productDto);
        modelAndView.setViewName("redirect:/productsAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/productsAdmin");
        ProductDto productDto = productService.getById(id);
        productService.delete(productDto);
        return modelAndView;
    }

}