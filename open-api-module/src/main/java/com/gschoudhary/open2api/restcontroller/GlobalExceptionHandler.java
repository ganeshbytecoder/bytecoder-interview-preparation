package com.gschoudhary.open2api.restcontroller;

import com.gschoudhary.open2api.restcontroller.Dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingHeaderException.class)
    public ResponseEntity<String> handleMissingHeaderException(MissingHeaderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseDto> handleValidationException(ValidationException ex) {

        return new ResponseEntity<>(ResponseDto.builder().status(404).message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    // Other exception handlers can be added here
}
