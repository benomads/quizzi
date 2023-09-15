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
    public ResponseEntity<Object> handleQuestionNotFoundException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException =
            new ApiException(
                    ZonedDateTime.now(ZoneId.of("UTC+6")),
                    httpStatus.value(),
                    httpStatus,
                    e.getMessage(),
                    request.getRequestURI()
            );


        return new ResponseEntity<>(apiException, httpStatus);
    }
}
