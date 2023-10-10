package com.benomads.quizzi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class QuizAlreadyExistsException extends RuntimeException{

    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
