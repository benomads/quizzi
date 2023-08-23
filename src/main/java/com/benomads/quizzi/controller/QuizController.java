package com.benomads.quizzi.controller;

import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.model.Quiz;
import com.benomads.quizzi.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("all-quizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                            @RequestParam(name = "numQ") int numberOfQuestions,
                                            @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }
}
