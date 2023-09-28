package com.benomads.quizzi.controller;

import com.benomads.quizzi.model.ApiResponse;
import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.service.QuestionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();

        return ResponseEntity
            .ok()
            .body(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Long id){
        Optional<Question> question = questionService.getQuestionById(id);

        return ResponseEntity
            .ok()
            .body(question);

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(
            @PathVariable String category) {

        List<Question> questions = questionService.getQuestionsByCategory(category);

        return ResponseEntity
            .ok()
            .body(questions);
    }

    @GetMapping("/level/{difficultyLevel}")
    public ResponseEntity<List<Question>> getQuestionsByDifficultyLevel(
            @PathVariable String difficultyLevel) {

        List<Question> questions = questionService.getQuestionsByDifficultyLevel(difficultyLevel);

        return ResponseEntity
            .ok()
            .body(questions);
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.addQuestion(question);
        return ResponseEntity
            .created(URI
                .create("/api/questions" + createdQuestion.getId()))
            .body(
                new ApiResponse(
                    true,
                    "Question created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeQuestion(@PathVariable Long id,
                                            @RequestBody Question question) {
        Question createdQuestion = questionService.changeQuestion(id, question);
        return ResponseEntity
            .ok()
            .body(
                String.format("Question with id=%d changed successfully \n Data: ", id)
                                                                    + createdQuestion);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity
            .ok()
            .body(
                new ApiResponse(
                    true,
                    String.format(
                        "Question with id=%d deleted successfully!", id)));
    }
}
