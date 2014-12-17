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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sbhachu on 05/12/2014.
 */
@RequestMapping("/api/v1")
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

            Object principal = getAuthentication().getPrincipal();
            User user = null;
            if(principal instanceof org.springframework.security.core.userdetails.User) {
                user = userService.getUser(((org.springframework.security.core.userdetails.User) principal).getUsername());
            } else {
                user = userService.getUser((String) principal);
            }
            return user;
        } else {
            throw new ServerException("Not Authenticated: " + getAuthentication());
        }
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
