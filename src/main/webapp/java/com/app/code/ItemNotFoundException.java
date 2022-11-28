package com.app.code;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends RuntimeException{
    private HttpStatus httpStatus;
    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
