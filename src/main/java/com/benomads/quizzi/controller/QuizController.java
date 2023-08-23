package com.benomads.quizzi.controller;

import com.benomads.quizzi.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> creatQuiz(@RequestParam String category,
                                            @RequestParam int numberOfQuestions,
                                            @RequestParam String title) {
        return quizService.creatQuiz(category, numberOfQuestions, title);
    }
}
