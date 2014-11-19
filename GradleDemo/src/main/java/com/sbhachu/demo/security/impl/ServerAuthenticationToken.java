package com.sbhachu.demo.security.impl;

import com.sbhachu.demo.models.UserModel;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class ServerAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

    private static final long serialVersionUID = 2642194654808411568L;

    private UserModel user;

    private Object credentials;

    public ServerAuthenticationToken(Authentication authentication, AuthorizedUserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.setAuthenticated(authentication.isAuthenticated());
        this.setDetails(authentication.getDetails());
        this.credentials = authentication.getCredentials();
        this.user = userDetails.getUser();
        this.setAuthenticated(true);
    }

    public Object getCredentials() {
        return credentials;
    }

    public Object getPrincipal() {
        return user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
