package com.aminov.controller;

import com.aminov.dto.ProductDto;
import com.aminov.model.Cart;
import com.aminov.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    private ProductService<ProductDto> productService;
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductService<ProductDto> productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cartPage(Authentication authentication,
                                           HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getItems().size() != 0)
            modelAndView.addObject("cartItems", cart.getItems());
        if (authentication != null)
            modelAndView.addObject("authentication", authentication);
        return modelAndView;
    }

    @RequestMapping(value = "/cart/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeItemFromCart(@PathVariable("id") int id,
                                      HttpSession session){
        this.cart.removeItem(this.productService.getById(id));
        session.setAttribute("cart", this.cart);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView orderPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        modelAndView.addObject("authentication", authentication);
        return modelAndView;
    }

}
