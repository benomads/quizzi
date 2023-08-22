package com.benomads.quizzi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "question")
public class QuestionController {

    @GetMapping("allQuestions")
    public String getAllQuestions() {
        return "Hi these are all questions: ";
    }
}
