package com.aminov.security;

import com.aminov.dto.UserDto;
import com.aminov.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
    private UserService<UserDto> userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserService(UserService<UserDto> userService) {
        this.userService = userService;
    }

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (this.userService.getByEmail(name) == null){
            throw new UsernameNotFoundException("User not found.");
        }
        if (!this.bCryptPasswordEncoder.matches(password, this.userService.getByEmail(name).getPassword())){
            throw new BadCredentialsException("Bad credentials.");
        }
        else
            logger.debug("Error with authentication via AuthenticationProvider class. Something went wrong.");
        UserDetails userDetails = this.userService.loadUserByUsername(name);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.userService.getByEmail(name).getRole()));
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
