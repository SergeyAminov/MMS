package com.aminov.controller;

import com.aminov.dto.CategoryDto;
import com.aminov.dto.ProductDto;
import com.aminov.messaging.MessageSender;
import com.aminov.model.Cart;
import com.aminov.service.interfaces.CategoryService;
import com.aminov.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private ProductService<ProductDto> productService;
    private CategoryService<CategoryDto> categoryService;
    private Cart cart;
    private MessageSender messageSender;

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

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
                                           HttpSession session,
                                           @RequestParam(name = "min-price", required = false) String minPrice,
                                           @RequestParam(name = "max-price", required = false) String maxPrice,
                                           @RequestParam(name = "min-weight", required = false) String minWeight,
                                           @RequestParam(name = "max-weight", required = false) String maxWeight,
                                           @RequestParam(name = "category", required = false) String category
                                           ) {
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("cart", new Cart(this.cart));
        modelAndView.setViewName("catalog");
        Map<String, String> params = new HashMap<>();
        messageSender.sendMessage();
        params.put("min-price", minPrice);
        params.put("max-price", maxPrice);
        params.put("min-weight", minWeight);
        params.put("max-weight", maxWeight);
        params.put("category", category);
        List<ProductDto> productDtoList = productService.getFilteredItems(params);
        modelAndView.addObject("productsList", productDtoList);
        modelAndView.addObject("categoryMap", this.categoryService.getIdTitleMap());
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
        modelAndView.addObject("categoryMap", this.categoryService.getIdTitleMap());
        modelAndView.addObject("productsList", productDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/products/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        ProductDto productDto = productService.getById(id);
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("categoryMap", this.categoryService.getIdTitleMap());
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
        modelAndView.addObject("categoryMap", this.categoryService.getIdTitleMap());
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