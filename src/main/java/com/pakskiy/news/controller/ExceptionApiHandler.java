package com.pakskiy.news.controller;

import com.pakskiy.news.exception.ErrorMessage;
import com.pakskiy.news.exception.OutOfLimitException;
import com.pakskiy.news.exception.RecordNotFoundException;
import com.pakskiy.news.exception.WrongFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<ErrorMessage> recordNotFoundException(RecordNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler({OutOfLimitException.class})
    public ResponseEntity<ErrorMessage> outOfLimitException(OutOfLimitException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler({WrongFormatException.class})
    public ResponseEntity<ErrorMessage> outOfLimitException(WrongFormatException exception) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(exception.getMessage()));
    }

}
