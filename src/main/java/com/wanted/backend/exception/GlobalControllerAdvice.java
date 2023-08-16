package com.wanted.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(WantedException.class)
    public ResponseEntity<?> applicationHandler(WantedException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().name());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validRequestException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        ObjectError objectError = methodArgumentNotValidException.getBindingResult()
                .getAllErrors().get(0);

        return ResponseEntity.badRequest().body(
                (objectError.getDefaultMessage()));
    }
}