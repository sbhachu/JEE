package com.sbhachu.demo.service;

import com.sbhachu.demo.exception.ServerDataAccessException;
import com.sbhachu.demo.models.UserModel;

import java.util.List;

public interface IUserService {
    public List<UserModel> getAllUsers() throws ServerDataAccessException;

    public UserModel getUser(Long id) throws ServerDataAccessException;

    public Long createUser(UserModel user) throws ServerDataAccessException;

    public boolean updateUser(UserModel user) throws ServerDataAccessException;

    public boolean removeUser(Long id) throws ServerDataAccessException;
}
