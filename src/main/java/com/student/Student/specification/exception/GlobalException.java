package com.student.Student.specification.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    private Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(value =AdminException.class)
    public ResponseEntity<?> handleTeamException(AdminException exception) {
        logger.error("Admin exception: {}", exception.getErrorMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(exception.getErrorMessage()).build(), exception.getStatus());
    }
}
