package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.exception.ServerException;
import com.sbhachu.oauth.demo.model.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RolesAllowed({"ROLE_USER"})
    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_USER"})
    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_USER"})
    @ResponseBody
    @RequestMapping(value = "/users/me", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getAuthenticatedUser() throws ServerException {
        return new ResponseEntity<User>(loadUserFromSecurityContext(), HttpStatus.OK);
    }
}
