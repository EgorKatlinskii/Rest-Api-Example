package com.example.testtaskntiteam.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@ControllerAdvice
public class CustomRestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var response = new Response(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ExceptionHandler({EntityNotFoundException.class, IndexOutOfBoundsException.class})
    public ResponseEntity<Object> handleException(Exception e) {
        var response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}