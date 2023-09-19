package com.benomads.quizzi.controller;

import com.benomads.quizzi.model.ApiResponse;
import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.entity.Quiz;
import com.benomads.quizzi.model.Response;
import com.benomads.quizzi.service.QuizService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizById(id));

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
    public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok().body(
            new ApiResponse(
                true,
                String.format(
                    "Quiz with id=%d deleted successfully!", id)));
    }
}
