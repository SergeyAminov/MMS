package com.aminov.controller;

import com.aminov.dto.UserDto;
import com.aminov.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginRegistrationController {
    private static final Logger logger = Logger.getLogger(LoginRegistrationController.class);
    private UserService<UserDto> userService;

    @Autowired
    public void setUserService(UserService<UserDto> userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        UserDto userDto = new UserDto();
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(logger.isDebugEnabled()){
                logger.debug("Trying to create new user...");
            }
            userService.registerNewUserAccount(userDto);
        } catch (Exception uaeEx) {
            logger.error("Error: ", uaeEx);
            modelAndView.addObject(
                    "message",
                    "An account for that username/email already exists."
            );
            return modelAndView;
        }
        if(logger.isDebugEnabled()){
            logger.debug("User was successfully created!");
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
