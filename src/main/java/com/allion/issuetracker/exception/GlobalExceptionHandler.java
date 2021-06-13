package com.allion.issuetracker.exception;


import com.allion.issuetracker.dto.GenericResponse;
import com.mongodb.MongoBulkWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException mavException, HttpServletRequest request) {
        return new ResponseEntity<>("Arguments are in correct", HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleValidationExceptions(
            HttpMessageNotReadableException handleValidationException, HttpServletRequest request) {
        return new ResponseEntity<>("Arguments are in correct", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MongoBulkWriteException.class)
    public ResponseEntity<?> handleDuplicateInsertionException(
            MongoBulkWriteException mongoBulkWriteException, HttpServletRequest request) {
        return new ResponseEntity<>(mongoBulkWriteException, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomIssueException.class)
    public ResponseEntity<GenericResponse> handleCustomIssueException(
            Exception customIssueException, HttpServletRequest request) {
        log.info("calling custom issue exception "+customIssueException.getMessage());
        GenericResponse responseDTO =
                new GenericResponse(HttpStatus.BAD_REQUEST, customIssueException.getMessage(), customIssueException.getClass());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
