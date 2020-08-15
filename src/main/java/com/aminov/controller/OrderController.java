package com.aminov.controller;

import com.aminov.dto.*;
import com.aminov.model.Cart;
import com.aminov.model.Order;
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

    private final Cart cart;
    private final ProductService<ProductDto> productService;
    private final AddressService<AddressDto> addressService;
    private final PaymentMethodService<PaymentMethodDto> paymentMethodService;
    private final DeliveryMethodService<DeliveryMethodDto> deliveryMethodService;
    private final DeliveryStatusService<DeliveryStatusDto> deliveryStatusService;
    private final PaymentStatusService<PaymentStatusDto> paymentStatusService;
    private final UserService<UserDto> userService;
    private final OrderService<OrderDto> orderService;
    private final OrderItemService<OrderItemDto> orderItemService;

    @Autowired
    public OrderController(PaymentStatusService<PaymentStatusDto> paymentStatusService,
                           DeliveryStatusService<DeliveryStatusDto> deliveryStatusService,
                           OrderItemService<OrderItemDto> orderItemService,
                           OrderService<OrderDto> orderService,
                           UserService<UserDto> userService,
                           AddressService<AddressDto> addressService,
                           PaymentMethodService<PaymentMethodDto> paymentMethodService,
                           DeliveryMethodService<DeliveryMethodDto> deliveryMethodService,
                           ProductService<ProductDto> productService,
                           Cart cart) {
        this.deliveryStatusService = deliveryStatusService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.userService = userService;
        this.paymentMethodService = paymentMethodService;
        this.deliveryMethodService = deliveryMethodService;
        this.productService = productService;
        this.addressService = addressService;
        this.paymentStatusService = paymentStatusService;
        this.cart = cart;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView getCartPage(Authentication authentication,
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
    public ModelAndView getOrderPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("order", "order", new OrderDto());
        int userId = this.userService.getByEmail(authentication.getName()).getId();
        modelAndView.addObject("authentication", authentication);
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("addressMap", this.addressService.getIdTitleMapByUserId(userId));
        modelAndView.addObject("paymentMethodMap", this.paymentMethodService.getIdTitleMap());
        modelAndView.addObject("deliveryMethodMap", this.deliveryMethodService.getIdTitleMap());
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView sendOrder(@ModelAttribute("order") OrderDto orderDto,
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

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public ModelAndView getOrderHistoryAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ordersAdmin");
        modelAndView.addObject("orderMap", this.orderService.getOrderMap());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/orders/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editOrderPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderEdit");
        OrderDto orderDto = orderService.getById(id);
        modelAndView.addObject("order", orderDto);
        modelAndView.addObject("paymentStatusMap", this.paymentStatusService.getIdTitleMap());
        modelAndView.addObject("deliveryStatusMap", this.deliveryStatusService.getIdTitleMap());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/orders/edit", method = RequestMethod.POST)
    public ModelAndView editOrder(@ModelAttribute("order") OrderDto orderDto) {
        ModelAndView modelAndView = new ModelAndView();
        orderService.edit(orderDto);
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/orders", method = RequestMethod.GET)
    public ModelAndView getOrderHistoryClient(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderHistory");
        int userId = this.userService.getByEmail(authentication.getName()).getId();
        modelAndView.addObject("orderMap", this.orderService.getOrderMap(userId));
        modelAndView.addObject("authentication", authentication);
        return modelAndView;
    }

}
