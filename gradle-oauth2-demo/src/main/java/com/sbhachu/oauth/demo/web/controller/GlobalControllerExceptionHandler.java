package com.sbhachu.oauth.demo.web.controller;

import com.sbhachu.oauth.demo.exception.ServerDataAccessException;
import com.sbhachu.oauth.demo.exception.ServerException;
import com.sbhachu.oauth.demo.model.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbhachu on 10/12/2014.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerExceptionHandler {

//    @ExceptionHandler(value = ServerException.class)
//    public ResponseEntity<ErrorInfo> handleServerException(ServerException e) {
//        ErrorInfo errorInfo = new ErrorInfo("500", "Internal Server Error", e.getMessage());
//        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value = ServerDataAccessException.class)
    public ResponseEntity<ErrorInfo> handleServerDataAccessException(ServerDataAccessException e) {
        ErrorInfo errorInfo = new ErrorInfo("400", "Bad Request", e.getMessage());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
