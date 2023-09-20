package com.benomads.quizzi.controller;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.model.ApiResponse;
import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.entity.Quiz;
import com.benomads.quizzi.model.Response;
import com.benomads.quizzi.service.QuestionService;
import com.benomads.quizzi.service.QuizService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        List<Quiz> quizzes = quizService.getAllQuizzes();

        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
        List<QuestionWrapper> questionWrappers = quizService.getQuizById(id);

        return ResponseEntity.ok(questionWrappers);
    }

    // This POST request create(assembling) quiz randomly with existing questions
    @PostMapping("/random")
    public ResponseEntity<ApiResponse> createQuizWithExistsQuestions(@RequestParam String category,
                                             @RequestParam("numQ") int numberOfQuestions,
                                             @RequestParam String title) {
        Quiz createdQuiz = quizService.createQuiz(category, numberOfQuestions, title);
        return ResponseEntity.created(URI.create("/api/quizzes" + createdQuiz.getId())).body(new ApiResponse(true, "Quiz created successfully!"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createQuiz(@RequestBody List<Question> questions) {
        Quiz createdQuiz = quizService.createQuiz(questions);
        return ResponseEntity.created(URI.create("/api/quizzes" + createdQuiz.getId()))
                             .body(new ApiResponse(true, "Quiz created successfully!"));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses) {
        Integer score = quizService.calculateScore(id, responses);
        return ResponseEntity.ok(String.format("Number of correct answers: %d", score));
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
