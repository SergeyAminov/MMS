package com.aminov.controller;

import com.aminov.dto.*;
import com.aminov.model.Cart;
import com.aminov.service.interfaces.*;
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
public class OrderController {

    private Cart cart;

    private ProductService<ProductDto> productService;
    private AddressService<AddressDto> addressService;
    private PaymentMethodService<PaymentMethodDto> paymentMethodService;
    private DeliveryMethodService<DeliveryMethodDto> deliveryMethodService;
    private CategoryService<CategoryDto> categoryService;
    private UserService<UserDto> userService;

    private OrderService<OrderDto> orderService;
    private OrderItemService<OrderItemDto> orderItemService;

    @Autowired
    public void setOrderService(OrderService<OrderDto> orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService<OrderItemDto> orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Autowired
    public void setUserService(UserService<UserDto> userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAddressService(AddressService<AddressDto> addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setPaymentMethodService(PaymentMethodService<PaymentMethodDto> paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Autowired
    public void setDeliveryMethodService(DeliveryMethodService<DeliveryMethodDto> deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    @Autowired
    public void setCategoryService(CategoryService<CategoryDto> categoryService) {
        this.categoryService = categoryService;
    }

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
        if (cart.getItemList().size() != 0)
            modelAndView.addObject("cartItems", cart.getItemList());
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
    public ModelAndView orderPage(Authentication authentication,
                                  HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        Cart cart = (Cart) session.getAttribute("cart");
        int userId = this.userService.getByEmail(authentication.getName()).getId();
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("authentication", authentication);
        modelAndView.addObject("addressMap", this.addressService.getIdTitleMapByUserId(userId));
        modelAndView.addObject("paymentMethodMap", this.paymentMethodService.getIdTitleMap());
        modelAndView.addObject("deliveryMethodMap", this.deliveryMethodService.getIdTitleMap());
        modelAndView.addObject("categoryMap", this.categoryService.getIdTitleMap());
        modelAndView.addObject("cartList", cart.getItemList());
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView doOrder(@ModelAttribute("order") OrderDto orderDto,
                                HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getItemList().size() != 0) {
            List<OrderItemDto> orderItemDtoList = this.orderItemService.getOrderItemDtoList(this.cart.getItemList());
            this.orderService.add(orderDto, orderItemDtoList);
            this.cart.getItemList().clear();
            session.setAttribute("cart", this.cart);
        }

        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }

}
