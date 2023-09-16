package com.benomads.quizzi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class QuestionAlreadyExistException extends RuntimeException {

    public QuestionAlreadyExistException(String message) {
        super(message);
    }
}
