package com.benomads.quizzi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestForCreatingRandomQuiz {

    private String category;
    private int numberOfQuestions;
    private String title;
}
