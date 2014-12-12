package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.exception.ServerException;
import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by sbhachu on 05/12/2014.
 */
public abstract class BaseController {

    @Autowired
    protected IUserService userService;

    protected boolean isAuthenticated() {
        return getAuthentication() != null
                && getAuthentication().isAuthenticated()
                && !(getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    protected User loadUserFromSecurityContext() throws ServerException {
        if (getAuthentication() instanceof OAuth2Authentication) {
            org.springframework.security.core.userdetails.User principal =
                    (org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal();

            if (principal != null) {
                return userService.getUser(principal.getUsername());
            }
        } else {
            throw new ServerException("Not Authenticated: " + getAuthentication());
        }

        return null;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
