package com.sbhachu.oauth.demo.model.dto;

import com.sbhachu.oauth.demo.model.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by sbhachu on 12/12/2014.
 */
public class RegisteredUserDTO {

    private User user;
    private OAuth2AccessToken accessToken;

    public RegisteredUserDTO() {
    }

    public RegisteredUserDTO(final User user, final OAuth2AccessToken accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public OAuth2AccessToken getoAuth2AccessToken() {
        return accessToken;
    }
}
