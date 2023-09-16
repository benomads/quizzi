package com.benomads.quizzi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Object> handleQuestionNotFoundException(Exception ex,
                                                                  HttpServletRequest request) { //We need to replace Exception.class
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException =
            new ApiException(
                    ZonedDateTime.now(ZoneId.of("UTC+6")),
                    httpStatus.value(),
                    httpStatus,
                    ex.getMessage(),
                    request.getRequestURI()
            );


        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(QuestionAlreadyExistException.class)
    public ResponseEntity<Object> handleQuestionAlreadyExistException(Exception ex,
                                                                      HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.FOUND;

        ApiException apiException =
            new ApiException(
                    ZonedDateTime.now(ZoneId.of("UTC+6")),
                    httpStatus.value(),
                    httpStatus,
                    ex.getMessage(),
                    request.getRequestURI()

            );

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
