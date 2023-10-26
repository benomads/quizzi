package com.benomads.quizzi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse { //TODO have to combine with exception.ApiException for timestamp, detail, path and etc.
    private boolean success;
    private String message;
}
