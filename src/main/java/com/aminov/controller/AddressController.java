package com.aminov.controller;

import com.aminov.dto.AddressDto;
import com.aminov.dto.UserDto;
import com.aminov.service.AddressService;
import com.aminov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddressController {

    private UserService<UserDto> userService;
    private AddressService<AddressDto> addressService;

    @Autowired
    public void setAddressService(AddressService<AddressDto> addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setUserService(UserService<UserDto> userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile/addresses", method = RequestMethod.GET)
    public ModelAndView addressesPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> addressIdList = this.userService
                .getByEmail(authentication.getName())
                .getAddressIdList();
        if (addressIdList != null){
            modelAndView.addObject("addressIdList", addressIdList);
            modelAndView.addObject("addressService", this.addressService);
            UserDto user = this.userService.getByEmail(authentication.getName());
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("addresses");
        return modelAndView;
    }
    /*
    @RequestMapping(value = "/profile/addresses/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        ProductDto productDto = productService.getById(id);
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("categoryService", this.categoryService);
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") AddressDto addressDto) {
        ModelAndView modelAndView = new ModelAndView();
        addressService.edit(addressDto);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/add", method = RequestMethod.GET)
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productEdit");
        modelAndView.addObject("categoryService", this.categoryService);
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") AddressDto addressDto){
        ModelAndView modelAndView = new ModelAndView();
        addressService.add(addressDto);
        modelAndView.setViewName("redirect:/profile/addresses");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/products");
        ProductDto productDto = productService.getById(id);
        productService.delete(productDto);
        return modelAndView;
    }
    */

}
