package com.damazon.backend.exception;

import org.springframework.http.HttpStatus;

public class CustomRunTimeException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus statusCode;

    public CustomRunTimeException(String message) {
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND;
        errorCode = "GENERAL_ERROR";
    }

    public CustomRunTimeException(String message, String errorCode, HttpStatus statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public  HttpStatus getStatusCode() {
        return statusCode;
    }
}
