package com.benomads.quizzi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ApiException {

    private final ZonedDateTime timestamp;
    private final int status;
    private final HttpStatus error;
    private final String message;
    private final String path;

}
