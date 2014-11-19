package com.sbhachu.demo.web.controller;

import com.sbhachu.demo.dto.UserDTO;
import com.sbhachu.demo.exception.ServerDataAccessException;
import com.sbhachu.demo.models.UserModel;
import com.sbhachu.demo.service.impl.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public UserDTO getUsers() {
        UserDTO dto = new UserDTO();

        try {
            List<UserModel> users = userService.getAllUsers();
            dto.setRecords(users);
            dto.setCount(users.size());
            dto.setStatus("OK");
        } catch (ServerDataAccessException exception) {
            String errorMessage = String.format("[E]: Unable to retrieve all users. [%1$s]", exception.toString());
            log.error("[E]: " + errorMessage);
        }

        return dto;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserModel getUser(@PathVariable Long id) {
        UserModel user = null;

        try {
            user = userService.getUser(id);
        } catch (ServerDataAccessException exception) {
            String errorMessage = String.format("[E]: Unable to retrieve all users. [%1$s]", exception.toString());
            log.error("[E]: " + errorMessage);
        }

        return user;
    }
}
