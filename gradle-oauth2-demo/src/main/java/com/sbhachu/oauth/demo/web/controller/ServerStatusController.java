package com.sbhachu.oauth.demo.web.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sbhachu on 05/12/2014.
 */
@RestController
@RequestMapping("/api/v1")
public class ServerStatusController {

    private static final Logger LOGGER = Logger.getLogger(ServerStatusController.class);

    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<String>("Version: 1.0", HttpStatus.OK);
    }
}
