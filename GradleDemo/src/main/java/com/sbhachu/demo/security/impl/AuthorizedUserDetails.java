package com.sbhachu.demo.security.impl;

import com.sbhachu.demo.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class AuthorizedUserDetails extends User {

    private static final long serialVersionUID = -416114345950448720L;

    private UserModel user;

    public AuthorizedUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                                 boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public AuthorizedUserDetails(UserModel user, String username, String password, boolean enabled,
                                 ArrayList<GrantedAuthority> authorities) throws IllegalArgumentException {
        super(username, password, enabled, true, true, true, authorities);
        this.user = user;
    }

    public AuthorizedUserDetails(UserModel user, String username, String password,
                                 ArrayList<GrantedAuthority> authorities) throws IllegalArgumentException {
        super(username, password, true, true, true, true, authorities);
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
