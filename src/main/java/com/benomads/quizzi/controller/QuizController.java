package com.benomads.quizzi.controller;

import com.benomads.quizzi.model.ApiResponse;
import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.model.Quiz;
import com.benomads.quizzi.model.Response;
import com.benomads.quizzi.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId) {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestParam String category,
                                            @RequestParam(name = "numQ") int numberOfQuestions,
                                            @RequestParam String title) {
        Quiz quiz = quizService.createQuiz(category, numberOfQuestions, title);
        if (quiz.getId() != null)
            return ResponseEntity.created(URI.create("/api/questions" + quiz.getId()))
                .body(new ApiResponse(true, "Question created successfully!"));
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Bad request!"));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses) {
        return quizService.calculateScore(id, responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok(
            new ApiResponse(true, "Quiz deleted successfully!"));
    }
}
