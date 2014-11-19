package com.sbhachu.demo.exception;

import org.springframework.dao.DataAccessException;

public class ServerDataAccessException extends DataAccessException {

    private static final long serialVersionUID = -3680517053243094581L;

    public ServerDataAccessException(String message) {
        super(message);
    }

    public ServerDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
