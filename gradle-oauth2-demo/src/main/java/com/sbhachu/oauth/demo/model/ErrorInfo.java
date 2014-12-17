package com.sbhachu.oauth.demo.model;

/**
 * Created by sbhachu on 13/12/2014.
 */
public class ErrorInfo {

    private String code;
    private String type;
    private String message;

    public ErrorInfo() {
    }

    public ErrorInfo(String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
