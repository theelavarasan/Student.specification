package com.student.Student.specification.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AdminException extends Exception{
    private HttpStatus status;
    private String errorMessage;
    public AdminException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
