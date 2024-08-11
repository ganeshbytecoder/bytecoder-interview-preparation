package com.gschoudhary.controllers.ExceptionHandler;

import com.gschoudhary.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MissingHeaderException.class, UsernameNotFoundException.class})
    public ResponseEntity<String> handleMissingHeaderException(MissingHeaderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseDto> handleValidationException(ValidationException ex) {

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setResponse("Error " + ex);
        responseDto.setStatus(404);
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    // Other exception handlers can be added here
}
