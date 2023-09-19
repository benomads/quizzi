package com.benomads.quizzi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException{

    public QuizNotFoundException(String message) {
        super(message);
    }
}
