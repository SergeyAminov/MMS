package com.aminov.controller;

import com.aminov.dto.CategoryDto;
import com.aminov.dto.ProductDto;
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

    private ProductService<ProductDto> productService;
    private CategoryService<CategoryDto> categoryDtoService;

    @Autowired
    public void setProductService(ProductService<ProductDto> productService){
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService<CategoryDto> categoryDtoService){
        this.categoryDtoService = categoryDtoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView productsPageClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");

        List<ProductDto> productDtoList = productService.allItems();
        modelAndView.addObject("productsList", productDtoList);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public ModelAndView productsPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productsAdmin");
        List<ProductDto> productDtoList = productService.allItems();
        modelAndView.addObject("productsList", productDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<CategoryDto> categoryDtoList = categoryDtoService.allItems();
        ProductDto productDto = productService.getById(id);
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("categoriesList", categoryDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") ProductDto productDto) {
        ModelAndView modelAndView = new ModelAndView();
        productService.edit(productDto);
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        List<CategoryDto> categories = categoryDtoService.allItems();
        modelAndView.addObject("categoriesList", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") ProductDto productDto){
        ModelAndView modelAndView = new ModelAndView();
        productService.add(productDto);
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products");
        ProductDto productDto = productService.getById(id);
        productService.delete(productDto);
        return modelAndView;
    }

}