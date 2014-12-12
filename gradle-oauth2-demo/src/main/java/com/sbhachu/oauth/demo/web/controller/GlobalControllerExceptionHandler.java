package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.exception.ServerDataAccessException;
import com.sbhachu.oauth.demo.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbhachu on 10/12/2014.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = ServerException.class)
    public ResponseEntity<String> handleServerException(ServerException e) {
        String errorMessage = String.format("500: Internal Server Error [%1$s]", e.getMessage());
        return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ServerDataAccessException.class)
    public ResponseEntity<String> handleServerDataAccessException(ServerDataAccessException e) {
        String errorMessage = String.format("400: Bad Request [%1$s]", e.getMessage());
        return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OAuth2Exception.class)
    public ResponseEntity<String> handleOAuth2Exception(OAuth2Exception e) {
        String errorMessage = String.format("401: Not Auth'd [%1$s]", e.getMessage());
        return new ResponseEntity<String>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
