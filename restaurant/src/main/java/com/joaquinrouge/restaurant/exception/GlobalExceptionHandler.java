package com.joaquinrouge.restaurant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ApiError> handleRestaurantNotFoundException(RestaurantNotFoundException e){
        return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
    }

    @ExceptionHandler(InvalidRestaurantDataException.class)
    public ResponseEntity<ApiError> handleInvalidRestaurantDataException(InvalidRestaurantDataException e){
        return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbiddenException(ForbiddenException e){
        return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
    }

    public record ApiError(String message){}

}
