package com.example.tanaqolapi.web.errors;


import com.example.tanaqolapi.exceptions.VehicleAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String , String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField() , error.getDefaultMessage()));
        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(VehicleAlreadyExistException.class)
    public ResponseEntity<Map<String , String>> handleValidationExceptions(VehicleAlreadyExistException exception){
        Map<String , String> error = new HashMap<>();
        error.put("error" , exception.getMessage());
        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }

}
