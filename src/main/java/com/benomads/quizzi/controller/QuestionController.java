package com.benomads.quizzi.controller;

import com.benomads.quizzi.Question;
import com.benomads.quizzi.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("all-questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
