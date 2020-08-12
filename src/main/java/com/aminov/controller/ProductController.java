package com.aminov.controller;

import com.aminov.dto.CategoryDto;
import com.aminov.dto.ProductDto;
import com.aminov.model.Cart;
import com.aminov.service.CategoryService;
import com.aminov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    private ProductService<ProductDto> productService;
    private CategoryService<CategoryDto> categoryService;
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductService<ProductDto> productService){
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService<CategoryDto> categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView productsPageClient(Authentication authentication,
                                           HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("cart", new Cart(this.cart));
        modelAndView.setViewName("catalog");
        List<ProductDto> productDtoList = productService.allItems();
        modelAndView.addObject("productsList", productDtoList);
        modelAndView.addObject("categoryMap", this.categoryService.getCategoryIdTitleMap());
        modelAndView.addObject("authentication", authentication);
        return modelAndView;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public ModelAndView addProductToCart(@PathVariable("id") int id,
                                         Authentication authentication,
                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.addItem(productService.getById(id));
        session.setAttribute("cart", cart);
        modelAndView.addObject("authentication", authentication);
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public ModelAndView productsPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productsAdmin");
        List<ProductDto> productDtoList = productService.allItems();
        modelAndView.addObject("categoryMap", this.categoryService.getCategoryIdTitleMap());
        modelAndView.addObject("productsList", productDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        ProductDto productDto = productService.getById(id);
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("categoryMap", this.categoryService.getCategoryIdTitleMap());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") ProductDto productDto) {
        ModelAndView modelAndView = new ModelAndView();
        productService.edit(productDto);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        modelAndView.addObject("categoryMap", this.categoryService.getCategoryIdTitleMap());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") ProductDto productDto){
        ModelAndView modelAndView = new ModelAndView();
        productService.add(productDto);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/products");
        ProductDto productDto = productService.getById(id);
        productService.delete(productDto);
        return modelAndView;
    }

}