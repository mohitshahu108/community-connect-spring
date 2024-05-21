package com.communityconnect.spring.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataAccessException.class, DuplicateKeyException.class})
    protected ResponseEntity<Object> handleDatabaseExceptions(DataAccessException ex, WebRequest request) {
        String errorMessage = ex.getLocalizedMessage();
        return buildResponseEntity(new CustomErrorResponse(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        String errorMessage = "An unexpected error occurred";
        return buildResponseEntity(new CustomErrorResponse(errorMessage));
    }

    private ResponseEntity<Object> buildResponseEntity(CustomErrorResponse customErrorResponse) {
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}