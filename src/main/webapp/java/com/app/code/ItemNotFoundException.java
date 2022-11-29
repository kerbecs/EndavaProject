package com.app.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(ItemNotFoundException.class);

    private HttpStatus httpStatus;
    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        logger.info(message);
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
