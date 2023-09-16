package com.benomads.quizzi.service;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.dao.QuestionDao;
import com.benomads.quizzi.exception.QuestionAlreadyExistException;
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
        if (!questionDao.existsQuestionByCategory(category))
            throw new QuestionNotFoundException(String.format("Questions with category - '%s' not found", category));

        return questionDao.findQuestionByCategory(category);
    }

    public Question addQuestion(Question question) {
        String questionTitle = question.getQuestionTitle();
        String category = question.getCategory();
        List<Question> questionsFromDB = questionDao.findQuestionByCategory(category);

        for (Question question1 : questionsFromDB) {
            if (question1.getQuestionTitle().equals(questionTitle)) {
                throw new QuestionAlreadyExistException(
                    "Question already exist. Question with id="
                        + question1.getId() + " have same title of question");
            }
        }
        return questionDao.save(question);
    }

    public void deleteQuestion(Long id) {
        Optional<Question> question = questionDao.findById(id);
        if (question.isEmpty())
            throw new QuestionNotFoundException(String.format("Question with id=%d doesn't exist", id));

        questionDao.deleteById(id);
    }


}
