package com.sbhachu.demo.security.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class ServerAuthenticationProvider extends DaoAuthenticationProvider {
    protected Authentication createSuccessAuthentication(Object object, Authentication authentication,
                                                         UserDetails userDetails) {
        return new ServerAuthenticationToken(authentication, (AuthorizedUserDetails) userDetails);
    }

    public boolean supports(Class<? extends Object> authentication) {
        return (ServerAuthenticationProvider.class.isAssignableFrom(authentication) || UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
