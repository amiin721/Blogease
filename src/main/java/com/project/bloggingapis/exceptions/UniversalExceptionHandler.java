package com.project.bloggingapis.exceptions;

import com.project.bloggingapis.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.security.InvalidAlgorithmParameterException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UniversalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(),HttpStatus.NOT_FOUND.value()), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidAlgorithmParameterException.class)
    public ResponseEntity<ApiResponse> handleInvalidCredentialsException(InvalidAlgorithmParameterException ex) {
        return new ResponseEntity<>(new ApiResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.toString(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleSQLIntegrityViolationException(){
        return new ResponseEntity<>(new ApiResponse("User already exists with this email",HttpStatusCode.valueOf(400).toString(),HttpStatusCode.valueOf(400).value()),HttpStatusCode.valueOf(400));
    }
}
