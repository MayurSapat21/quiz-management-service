package com.ms.question_service.service;


import com.ms.question_service.dao.QuestionRepository;
import com.ms.question_service.model.Question;
import com.ms.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository repositoryQuestion;
//
    public Optional<Question> getQuestion(Integer id) {
        return repositoryQuestion.findById(id);
    }

    public List<Question> getQustions() {
        return repositoryQuestion.findAll();
    }
//
    public List<Question> getQustionByCategory(String category) {
        return repositoryQuestion.findByCategory(category);
    }

    public List<Question> getQuestionList(String category, Integer ListOfNumber) {
        return repositoryQuestion.findRandomQuestionsByCategory(category, ListOfNumber);
    }

    public Question saveQuestion(Question question) {
        return repositoryQuestion.save(question);
    }

    public List<Integer> getQuestionIdList(String category, Integer ListOfNumber) {
        return repositoryQuestion.findRandomQuestionsIdByCategory(category, ListOfNumber);
    }

    public Optional<Question> getAnswer(String answer) {
        return repositoryQuestion.findByRightAnswer(answer);
    }


    public Question getQuestionFromId(Integer id) {
        Optional<Question> question = repositoryQuestion.findById(id);
        return question.orElse(null);
    }


    public Integer getSocre(List<Response> responses) {
        int score = 0;
        for (Response response : responses) {
            Question question = repositoryQuestion.findById(response.getId()).get();
            if (response.getAnswer().equals(question.getRightAnswer())) {
                score++;
            }
        }
        return score;
    }
}
