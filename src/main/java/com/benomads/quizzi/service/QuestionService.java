package com.benomads.quizzi.service;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.dao.QuestionDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        if (!questionDao.existsQuestionByCategory(category))
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(
            questionDao
            .findQuestionByCategory(category),
            HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
