package com.damazon.backend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, String> fieldErrors = errors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> notReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        // Check if the cause is InvalidFormatException (Jackson throws this for enum mismatches)
        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            String fieldName = "";
            if (!invalidFormatException.getPath().isEmpty()) {
                fieldName = invalidFormatException.getPath().get(0).getFieldName();
            }
            errors.put(fieldName.isEmpty() ? "error" : fieldName,
                    "Invalid value provided. Allowed values: [ELECTRONICS, MOBILES, HOME_APPLIANCES, FURNITURES]");
        } else {
            errors.put("error", "Malformed JSON request or invalid input format.");
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomRunTimeException.class)
    public ResponseEntity<?> customRunTimeException(HttpServletRequest request, CustomRunTimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "error", ex.getErrorCode(),
                        "path", request.getRequestURI(),
                        "status", ex.getStatusCode(),
                        "timeStamp", LocalDateTime.now(),
                        "message", ex.getMessage()
                )
        );
    }

}
