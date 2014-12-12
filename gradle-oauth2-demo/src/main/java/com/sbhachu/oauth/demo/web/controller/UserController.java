package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.model.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/user/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}
