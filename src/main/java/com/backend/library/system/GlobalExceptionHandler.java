package com.backend.library.system;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    //This exception handler is for when we are trying to retrieve an entity that doesn't exist
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    //This exception handler is for when the access token is expired
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(ServletException.class)
    //This exception handler is for when the access cookie is not present in the request to any of endpoints
    public ResponseEntity<?> handleUnauthorizedException(ServletException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //This exception handler is for when the validation constraints on the entities is not respected.
    //For each field that doesn't match the constraint, we show it with its error message
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), "Does not match the defined pattern"));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    //This exception handler is for when the user enters a username and password that do not match
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    //This exception handler is for all other exceptions that aren't mentioned above
    public ResponseEntity<?> handleGenericException(Exception e){
        return new ResponseEntity<>("An error has occurred, please refer to the Support Team", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
