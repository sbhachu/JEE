package com.sbhachu.oauth.demo.service;

import com.sbhachu.oauth.demo.exception.ServerDataAccessException;
import com.sbhachu.oauth.demo.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers() throws ServerDataAccessException;

    public User getUser(Long id) throws ServerDataAccessException;

    public User getUser(String emailAddress) throws ServerDataAccessException;

    public User createUser(User user) throws ServerDataAccessException;

    public boolean updateUser(User user) throws ServerDataAccessException;

    public boolean removeUser(Long id) throws ServerDataAccessException;
}
