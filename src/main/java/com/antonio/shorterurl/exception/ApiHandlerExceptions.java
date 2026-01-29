package com.antonio.shorterurl.exception;

import com.antonio.shorterurl.dto.ExceptionDTO;
import com.antonio.shorterurl.exception.exceptions.UrlExpiredException;
import com.antonio.shorterurl.exception.exceptions.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiHandlerExceptions {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ExceptionDTO> urlNotFound (UrlNotFoundException ex) {

        var error = new ExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
                );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<ExceptionDTO> urlExpired (UrlExpiredException ex) {

        var error = new ExceptionDTO(
                HttpStatus.GONE.value(),
                ex.getMessage()
                );

        return ResponseEntity.status(HttpStatus.GONE).body(error);
    }

}

