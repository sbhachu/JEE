package com.sbhachu.oauth.demo.model.security;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by sbhachu on 24/11/2014.
 */
@Entity
@Table(name = "oauth_refresh_token")
public class OAuthRefreshToken {
    private String tokenId;
    private byte[] token;
    private byte[] authentication;

    public OAuthRefreshToken() {
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

    @Column(name = "authentication")
    public byte[] getAuthentication() {
        return authentication;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}
