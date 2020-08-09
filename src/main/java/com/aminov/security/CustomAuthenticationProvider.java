package com.aminov.security;

import com.aminov.dto.UserDto;
import com.aminov.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
    private UserService<UserDto> userService;

    @Autowired
    public void setUserService(UserService<UserDto> userService) {
        this.userService = userService;
    }

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ((this.userService.getByEmail(name) != null) &&
                (this.userService.getByEmail(name).getPassword().equals(password))){
            UserDetails userDetails = this.userService.loadUserByUsername(name);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(this.userService.getByEmail(name).getRole()));
            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
        }
        else{
            logger.debug("Error with authentication via AuthenticationProvider class.");
            return null;
        }

    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
