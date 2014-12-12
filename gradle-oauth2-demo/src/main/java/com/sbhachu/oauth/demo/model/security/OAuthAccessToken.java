package com.sbhachu.oauth.demo.model.security;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by sbhachu on 24/11/2014.
 */
@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken {

    private String tokenId;
    private byte[] token;
    private String authenticationId;
    private String username;
    private String clientId;
    private byte[] authentication;
    private String refreshToken;

    public OAuthAccessToken() {
    }

    @Id
    @Column(name = "token_id")
    public String getTokenId() {
        return tokenId;
    }

    @Column(name = "token")
    public byte[] getToken() {
        return token;
    }

    @Column(name = "authentication_id")
    public String getAuthenticationId() {
        return authenticationId;
    }

    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    @Column(name = "authentication")
    public byte[] getAuthentication() {
        return authentication;
    }

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
