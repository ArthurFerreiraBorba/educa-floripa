package com.fmt.educafloripa.infra.exception;

import com.fmt.educafloripa.infra.exception.error.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler (RuntimeException.class)
    public ResponseEntity<String> handler(RuntimeException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler (NotFoundException.class)
    public ResponseEntity<String> handler(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler (Invalid.class)
    public ResponseEntity<String> handler(Invalid e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler (UnauthorizedRole.class)
    public ResponseEntity<String> handler(UnauthorizedRole e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }
}
