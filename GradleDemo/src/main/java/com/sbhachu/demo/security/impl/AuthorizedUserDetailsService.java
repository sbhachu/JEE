package com.sbhachu.demo.security.impl;

import com.sbhachu.demo.dao.impl.UserModelDAO;
import com.sbhachu.demo.exception.ServerDataAccessException;
import com.sbhachu.demo.models.UserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public class AuthorizedUserDetailsService implements UserDetailsService {
    private static Logger log = Logger.getLogger(AuthorizedUserDetailsService.class);

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";

    private UserModelDAO dao;

    @Autowired
    public void setUserModelDAO(UserModelDAO dao) {
        this.dao = dao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        AuthorizedUserDetails userDetails;
        UserModel user;

        try {
            if (username.isEmpty()) {
                throw new UsernameNotFoundException("Please provide a valid username.");
            }

            user = dao.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("[E] Account for user [%s] not found.", username));
            } else {
                ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

                authorities.add(new GrantedAuthorityImpl(ROLE_USER));

                if (user.getRole() == UserModel.ROLE_ADMINISTRATOR) {
                    authorities.add(new GrantedAuthorityImpl(ROLE_ADMINISTRATOR));
                } else if (user.getRole() == UserModel.ROLE_MANAGER) {
                    authorities.add(new GrantedAuthorityImpl(ROLE_ADMINISTRATOR));
                    authorities.add(new GrantedAuthorityImpl(ROLE_MANAGER));
                }

                userDetails = new AuthorizedUserDetails(user, user.getUsername(), user.getPassword(),
                        user.getEnabled(), authorities);
            }
        } catch (Throwable cause) {
            String message = String.format("[E] Account for user [%s] not found.", username);
            log.error(message, cause);
            throw new ServerDataAccessException(message, cause);
        }

        return userDetails;
    }

}
