package com.sbhachu.oauth.demo.security;

import com.sbhachu.oauth.demo.dao.impl.UserDaoImpl;
import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.model.security.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service(value = "userDetailsService")
@Transactional
public class AuthorizedUserDetailsService implements UserDetailsService {
    private static Logger log = Logger.getLogger(AuthorizedUserDetailsService.class);

    private UserDaoImpl userDao;

    @Autowired
    public void setUserModelDAO(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException, DataAccessException {

        User user = userDao.findByEmailAddress(emailAddress);

        if (user != null) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            boolean enabled = user.getEnabled();
            boolean accountNonExpired = user.getEnabled();
            boolean credentialsNonExpired = user.getEnabled();
            boolean accountNonLocked = user.getEnabled();

            authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));

            if (user.getRole() == Role.ROLE_ADMIN) {
                authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
            }

            org.springframework.security.core.userdetails.User authorizedUser =
                    new org.springframework.security.core.userdetails.User(emailAddress,
                            user.getPassword(),
                            enabled,
                            accountNonExpired,
                            credentialsNonExpired,
                            accountNonLocked,
                            authorities);

            return authorizedUser;
        } else {
            throw new UsernameNotFoundException(String.format("[E] Account for user [%s] not found.", emailAddress));
        }
    }

}
