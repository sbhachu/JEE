package com.sbhachu.demo.exception;

public class ServerException extends Exception {

    private static final long serialVersionUID = 6723678391980621245L;

    private String errorCode;

    private Boolean loggable;

    public ServerException() {
    }

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

    public ServerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServerException(String message, String errorCode, Boolean loggable) {
        super(message);
        this.errorCode = errorCode;
        this.loggable = loggable;
    }

    public Boolean isLoggable() {
        return loggable;
    }

    public void setLoggable(Boolean loggable) {
        this.loggable = loggable;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
