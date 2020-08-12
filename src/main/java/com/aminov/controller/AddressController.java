package com.aminov.controller;

import com.aminov.dto.AddressDto;
import com.aminov.dto.UserDto;
import com.aminov.service.interfaces.AddressService;
import com.aminov.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        UserDto user = this.userService.getByEmail(authentication.getName());
        List<Integer> addressIdList = this.addressService.getAddressIdListByUserId(user.getId());
        if (addressIdList != null){
            Map<Integer, String> addressMap = new HashMap<>();
            for (int id : addressIdList){
                addressMap.put(id, this.addressService.getById(id).toString());
            }
            modelAndView.addObject("addressMap", addressMap);
            modelAndView.addObject("user", user);
        }
        modelAndView.addObject("authentication", authentication);
        modelAndView.setViewName("addresses");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editAddressPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addressEdit");
        AddressDto addressDto = addressService.getById(id);
        modelAndView.addObject("address", addressDto);
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/edit", method = RequestMethod.POST)
    public ModelAndView editAddress(@ModelAttribute("product") AddressDto addressDto) {
        ModelAndView modelAndView = new ModelAndView();
        addressService.edit(addressDto);
        modelAndView.setViewName("redirect:/profile/addresses");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/add", method = RequestMethod.GET)
    public ModelAndView addAddressPage(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addressEdit");
        int userId = this.userService.getByEmail(authentication.getName()).getId();
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("authentication", authentication);
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/add", method = RequestMethod.POST)
    public ModelAndView addAddress(@ModelAttribute("product") AddressDto addressDto){
        ModelAndView modelAndView = new ModelAndView();
        this.addressService.add(addressDto);
        modelAndView.setViewName("redirect:/profile/addresses");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/addresses/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAddress(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/addresses");
        AddressDto addressDto = this.addressService.getById(id);
        this.addressService.delete(addressDto);
        return modelAndView;
    }

}
