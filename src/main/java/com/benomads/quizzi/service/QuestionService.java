package com.benomads.quizzi.service;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.dao.QuestionDao;
import com.benomads.quizzi.exception.QuestionNotFoundException;
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

    public Optional<Question> getQuestionById(Long id)  {
        Optional<Question> question = questionDao.findById(id);
        if (question.isEmpty())
            throw new QuestionNotFoundException(String.format("Question with id=%d not found", id));

        return question;
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions = questionDao.findQuestionByCategory(category);

        if (questions.isEmpty())
            throw new QuestionNotFoundException(String.format("Questions with category - '%s' not found", category));

        return questions;
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public void deleteQuestion(Long id) {
        questionDao.deleteById(id);
    }


}
