package com.benomads.quizzi.controller;

import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.model.Quiz;
import com.benomads.quizzi.model.Response;
import com.benomads.quizzi.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping()
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizQuestions(id));
    }

    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                            @RequestParam(name = "numQ") int numberOfQuestions,
                                            @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses) {
        return quizService.calculateScore(id, responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id) {

        return ResponseEntity.ok( quizService.deleteQuiz(id));
    }
}
