package com.mickey.careerbridge_api.exceptions;

import com.mickey.careerbridge_api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                Map.of(
                        "success",false,
                        "message","Validation Failed",
                        "errors",errors
                ));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicateResource(DuplicateResourceException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                false,
                ex.getMessage(),
                ex.getCause()
        ));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidException(InvalidCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        ex.getCause()
                )
        );
    }
}
