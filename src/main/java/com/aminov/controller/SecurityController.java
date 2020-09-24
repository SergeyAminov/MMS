package com.aminov.controller;

import com.aminov.dto.UserDto;
//import com.aminov.messaging.MessageSender;
import com.aminov.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SecurityController {
    private static final Logger logger = Logger.getLogger(SecurityController.class);
    private UserService<UserDto> userService;
    private AuthenticationProvider authenticationProvider;
    /*private MessageSender messageSender;

    @Lazy
    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }*/

    @Autowired
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

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
        modelAndView.setViewName("redirect:/catalog");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationProvider.authenticate(authentication);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        //this.messageSender.sendMessage();
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
        modelAndView.setViewName("redirect:/catalog");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profilePage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = this.userService.getByEmail(authentication.getName());
        if (authentication.getName() == null)
            modelAndView.setViewName("redirect:/login");
        else{
            modelAndView.addObject("user", user);
            modelAndView.addObject("authentication", authentication);
            modelAndView.setViewName("profile");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public ModelAndView editProfilePage(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = this.userService.getByEmail(authentication.getName());
        modelAndView.addObject("user", userDto);
        modelAndView.addObject("authentication", authentication);
        modelAndView.setViewName("profileEdit");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("user") UserDto userDto){
        ModelAndView modelAndView = new ModelAndView();

        UserDto user = this.userService.getById(userDto.getId());
        userDto.setAddressIdList(user.getAddressIdList());
        userDto.setOrderIdList(user.getOrderIdList());
        userDto.setMatchingPassword(user.getMatchingPassword());
        this.userService.edit(userDto);

        modelAndView.setViewName("redirect:/profile/addresses");
        return modelAndView;
    }

}
