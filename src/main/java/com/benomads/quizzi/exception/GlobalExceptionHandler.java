package com.benomads.quizzi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus httpNotFoundErrorStatus = HttpStatus.NOT_FOUND;
    private final HttpStatus httpFoundErrorStatus = HttpStatus.FOUND;


    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Object> handleQuestionNotFoundException(Exception ex, //We need to replace Exception.class
                                                                  HttpServletRequest request) {

        ApiException apiException = getApiException(ex, request, httpNotFoundErrorStatus);
        return new ResponseEntity<>(apiException, httpNotFoundErrorStatus);
    }

    @ExceptionHandler(QuestionAlreadyExistException.class)
    public ResponseEntity<Object> handleQuestionAlreadyExistException(Exception ex,
                                                                      HttpServletRequest request) {

        ApiException apiException = getApiException(ex, request, httpFoundErrorStatus);
        return new ResponseEntity<>(apiException, httpFoundErrorStatus);
    }


    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Object> handleQuizNotFoundException(Exception ex,
                                                              HttpServletRequest request) {

        ApiException apiException = getApiException(ex, request, httpNotFoundErrorStatus);
        return new ResponseEntity<>(apiException, httpNotFoundErrorStatus);
    }

    @ExceptionHandler(QuizAlreadyExistsException.class)
    public ResponseEntity<Object> handleQuizAlreadyExistException(Exception ex,
                                                                  HttpServletRequest request) {

        ApiException apiException = getApiException(ex, request, httpFoundErrorStatus);
        return new ResponseEntity<>(apiException, httpFoundErrorStatus);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex, //We need to replace Exception.class
                                                                  HttpServletRequest request) {

        ApiException apiException = getApiException(ex, request, httpNotFoundErrorStatus);
        return new ResponseEntity<>(apiException, httpNotFoundErrorStatus);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException ex) {
//
//        Map<String, String> errorResponse = new HashMap<>();
//
//        errorResponse.put("error", ex);
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    private static ApiException getApiException(Exception ex, HttpServletRequest request, HttpStatus httpStatus) {
        return
            new ApiException(
                ZonedDateTime.now(ZoneId.of("UTC+6")),
                httpStatus.value(),
                httpStatus,
                ex.getMessage(),
                request.getRequestURI()
            );
    }



}
