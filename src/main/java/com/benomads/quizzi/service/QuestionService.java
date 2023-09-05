package com.benomads.quizzi.service;

import com.benomads.quizzi.model.Question;
import com.benomads.quizzi.dao.QuestionDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionDao.findById(id);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findQuestionByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public void deleteQuestion(Long id) {
        questionDao.deleteById(id);
    }


}
