package com.sbhachu.oauth.demo.service.impl;

import com.sbhachu.oauth.demo.dao.impl.UserDaoImpl;
import com.sbhachu.oauth.demo.exception.ServerDataAccessException;
import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.model.security.Role;
import com.sbhachu.oauth.demo.service.IUserService;
import com.sbhachu.oauth.demo.util.MD5EncoderUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    public UserDaoImpl userDao;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws ServerDataAccessException {
        List<User> users = userDao.findAll();

        if (users == null) {
            LOGGER.error("[E]: No User Record(s) Found.");
            throw new ServerDataAccessException("[E]: No User Record(s) Found.");
        }

        LOGGER.info(String.format("[I]: %s User Records Found.", users.size()));

        return users;
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) throws ServerDataAccessException {
        User user = userDao.findById(id);

        if (user == null) {
            LOGGER.error(String.format("[E]: No User Record with ID [%s] Found.", id));
            throw new ServerDataAccessException(String.format("[E]: No User Record with ID [%s] Found.", id));
        }

        LOGGER.info(String.format("[I]: User [%s] Record Found.", id));

        return user;
    }

    @Transactional(readOnly = true)
    public User getUser(String emailAddress) throws ServerDataAccessException {
        User user = userDao.findByEmailAddress(emailAddress);

        if (user == null) {
            LOGGER.error(String.format("[E]: No User Record with email address [%s] Found.", emailAddress));
            throw new ServerDataAccessException(String.format("[E]: No User Record with email address [%s] Found.", emailAddress));
        }

        LOGGER.info(String.format("[I]: User [%s] Record Found.", emailAddress));

        return user;
    }

    @Transactional(readOnly = false)
    public User createUser(User user) throws ServerDataAccessException {
        if (user == null)
            throw new ServerDataAccessException("[E]: Invalid argument.");

        if (StringUtils.isEmpty(user.getFirstName()))
            throw new ServerDataAccessException("[E]: First name must be provided.");

        if (StringUtils.isEmpty(user.getLastName()))
            throw new ServerDataAccessException("[E]: Last name must be provided.");

        if (StringUtils.isEmpty(user.getEmail()))
            throw new ServerDataAccessException("[E]: Email address must be provided.");

        if (StringUtils.isEmpty(user.getPassword()))
            throw new ServerDataAccessException("[E]: Password must be provided.");

        if (userDao.findByEmailAddress(user.getEmail()) != null) {
            String errorMessage = String.format("[E]: A user with the email address [%s] already exists.", user.getEmail());
            throw new ServerDataAccessException(errorMessage);
        }

        // ensure new accounts are created with minimal privileges
        user.setRole(Role.ROLE_USER);

        // ensure new accounts are enabled (this flag is usually used
        // for email account activation, but for simplicity enable here)
        user.setEnabled(true);

        user.setDateCreated(new Date());
        user.setDateModified(new Date());
        user.setLastLogin(new Date());

        Long userId = userDao.create(user);

        if (userId == null) {
            LOGGER.error("[E]: Could not create User - Database User ID is null.");
            throw new ServerDataAccessException("[E]: An error occurred while creating the User, please try again.");
        }

        user.setId(userId);

        LOGGER.info(String.format("[I]: Created new User: %s ID: %s ", user.getEmail(), userId));

        return user;
    }

    @Transactional(readOnly = false)
    @Secured("ROLE_USER")
    public boolean updateUser(User user) throws ServerDataAccessException {
        return false;
    }

    @Transactional(readOnly = false)
    @Secured("ROLE_USER")
    public boolean removeUser(Long id) throws ServerDataAccessException {
        return false;
    }


}
