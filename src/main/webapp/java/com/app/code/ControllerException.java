package com.app.code;

import org.hibernate.cfg.beanvalidation.BeanValidationEventListener;
import org.hibernate.cfg.beanvalidation.BeanValidationIntegrator;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler
    public ResponseEntity<CustomerError> notFound(ItemNotFoundException exception){
        CustomerError error = new CustomerError(exception.getMessage(), String.valueOf(exception.getHttpStatus().value()),(int) System.currentTimeMillis());

        return new ResponseEntity<>(error,exception.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<CustomerError> validation(BeanDefinitionValidationException exception){
        CustomerError error = new CustomerError("You're data are not correct. Please check them and try again",String.valueOf(HttpStatus.BAD_REQUEST.value()),(int)System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<CustomerError> unknown(Exception exception){
        CustomerError error = new CustomerError("An Unknown error has occurred. Please contact the staff.",String.valueOf(HttpStatus.BAD_REQUEST.value()),(int)System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
