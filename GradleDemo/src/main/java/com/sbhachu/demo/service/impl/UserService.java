package com.sbhachu.demo.service.impl;

import com.sbhachu.demo.dao.impl.UserModelDAO;
import com.sbhachu.demo.exception.ServerDataAccessException;
import com.sbhachu.demo.models.UserModel;
import com.sbhachu.demo.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    private static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    public UserModelDAO userModelDAO;

    @Transactional(readOnly = true)
    @Secured("ROLE_USER")
    public List<UserModel> getAllUsers() throws ServerDataAccessException {
        List<UserModel> users = userModelDAO.findAll();

        if (users == null) {
            log.error("[E]: No User Record(s) Found.");
            throw new ServerDataAccessException("[E]: No User Record(s) Found.");
        }

        log.info(String.format("[I]: %s User Records Found.", users.size()));

        return users;
    }

    @Transactional(readOnly = true)
    @Secured("ROLE_USER")
    public UserModel getUser(Long id) throws ServerDataAccessException {
        UserModel user = userModelDAO.findById(id);

        if (user == null) {
            log.error(String.format("[E]: No User Record with ID [%s] Found.", id));
            throw new ServerDataAccessException(String.format("[E]: No User Record with ID [%s] Found.", id));
        }

        log.info(String.format("[I]: User [%s] Record Found.", id));

        return user;
    }

    @Transactional(readOnly = false)
    public UserModel createUser(UserModel user) throws ServerDataAccessException {
        if (user == null)
            throw new ServerDataAccessException("[E]: Invalid argument.");

        if (user.getUsername() == null)
            throw new ServerDataAccessException("[E]: Username must be provided.");

        if (userModelDAO.findByUsername(user.getUsername()) != null) {
            String errorMessage = String.format("[E]: A user with username [%s] already exists.", user.getUsername());
            throw new ServerDataAccessException(errorMessage);
        }

        // ensure new accounts are created with minimal privileges
        user.setRole(UserModel.ROLE_USER);

        // ensure new accounts are enabled (this flag is usually used for email account activation, but for simplicity enable here)
        user.setEnabled(true);

        user.setDateCreated(new Date());
        user.setDateModified(new Date());
        user.setLastLogin(new Date());

        Long userId = userModelDAO.create(user);

        if (userId == null) {
            log.error("[E]: Could not create User - Database User ID is null.");
            throw new ServerDataAccessException("[E]: An error occurred while creating the User, please try again.");
        }

        user.setId(userId);

        log.info(String.format("[I]: Created new UserModel: %s ID: %s ", user.getUsername(), userId));

        return user;
    }

    @Transactional(readOnly = false)
    @Secured("ROLE_USER")
    public boolean updateUser(UserModel user) throws ServerDataAccessException {
        return false;
    }

    @Transactional(readOnly = false)
    @Secured("ROLE_MANAGER")
    public boolean removeUser(Long id) throws ServerDataAccessException {
        return false;
    }
}
