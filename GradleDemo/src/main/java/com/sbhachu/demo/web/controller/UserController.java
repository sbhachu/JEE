package com.sbhachu.demo.web.controller;

import com.sbhachu.demo.models.UserModel;
import com.sbhachu.demo.service.impl.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserModel>> getUsers() {
        return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        return new ResponseEntity<UserModel>(userService.getUser(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/user/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        return new ResponseEntity<UserModel>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}
