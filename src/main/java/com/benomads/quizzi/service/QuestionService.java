package com.benomads.quizzi.service;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.dao.QuestionDao;
import com.benomads.quizzi.exception.QuestionAlreadyExistException;
import com.benomads.quizzi.exception.QuestionNotFoundException;
import com.benomads.quizzi.exception.QuizNotFoundException;
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
            throw new QuestionNotFoundException(String.format(
                "Question with id=%d not found", id));

        return question;
    }

    public List<Question> getQuestionsByCategory(String category) {
        if (!questionDao.existsQuestionByCategory(category))
            throw new QuestionNotFoundException(String.format(
                "Questions with category - '%s' not found", category));

        return questionDao.findQuestionByCategory(category);
    }

    public Question addQuestion(Question question) {
        String questionTitleFromRequest = question.getQuestionTitle();

        if (questionDao.existsQuestionByQuestionTitle(questionTitleFromRequest))
            throw new QuestionAlreadyExistException(
                "Question already exist. Question with id="
                    + question.getQuestionId() + " have same title of question");

        return questionDao.save(question);
    }

    public Question changeQuestion(Long id,
                                   Question question) {
        if (!questionDao.existsById(id))
            throw new QuestionNotFoundException(String.format(
                "Question with id=%d not found", id));

        return questionDao.save(question);
    }

    public void deleteQuestion(Long id) {
        Optional<Question> question = questionDao.findById(id);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException(String.format(
                "Question with id=%d doesn't exist", id));
        }

        questionDao.deleteById(id);
    }


}
