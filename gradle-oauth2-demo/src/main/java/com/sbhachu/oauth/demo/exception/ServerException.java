package com.sbhachu.oauth.demo.exception;

/**
 * Created by sbhachu on 05/12/2014.
 */
public class ServerException extends Exception {

    private int errorCode;

    private boolean loggable;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(String message, Boolean loggable) {
        super(message);
        this.loggable = loggable;
    }

    public ServerException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServerException(String message, int errorCode, Boolean loggable) {
        super(message);
        this.errorCode = errorCode;
        this.loggable = loggable;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isLoggable() {
        return loggable;
    }

    public void setLoggable(boolean loggable) {
        this.loggable = loggable;
    }
}
