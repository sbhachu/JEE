package com.sbhachu.demo.web.controller;

import com.sbhachu.demo.exception.ServerDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbhachu on 20/11/2014.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ServerDataAccessException.class)
    public ResponseEntity<String> handleServerDataAccessException(ServerDataAccessException e) {
        String errorMessage = String.format("400: Bad Request [%1$s]", e.getMessage());
        return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
