package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.exception.ServerException;
import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.model.dto.RegisteredUserDTO;
import com.sbhachu.oauth.demo.model.security.Role;
import com.sbhachu.oauth.demo.security.TokenGenerator;
import com.sbhachu.oauth.demo.util.MD5EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 12/12/2014.
 */
@RestController
public class RegistrationController extends BaseController {

    @Autowired
    private TokenGenerator tokenGenerator;


    @PermitAll
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisteredUserDTO> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        OAuth2AccessToken accessToken = tokenGenerator.createToken(newUser);

        return new ResponseEntity<RegisteredUserDTO>(new RegisteredUserDTO(newUser, accessToken), HttpStatus.CREATED);
    }



}
