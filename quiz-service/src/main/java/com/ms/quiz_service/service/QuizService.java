package com.ms.quiz_service.service;

import com.ms.quiz_service.DTO.QuestionDTO;
import com.ms.quiz_service.repository.QuizRepository;
import com.ms.quiz_service.feign.QuizInterface;
import com.ms.quiz_service.model.Quiz;
import com.ms.quiz_service.model.Response;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository repositoryQuiz;

    @Autowired
    QuizInterface interfaceQuiz;
//
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> listResponseEntity = interfaceQuiz.generateQuestions(category, numQ).getBody();
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(listResponseEntity);
        repositoryQuiz.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestion(Integer quizId) {
        Optional<Quiz> quiz = repositoryQuiz.findById(quizId);
        List<Integer> questionIds = new ArrayList<>();
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        if (quiz.isPresent()) {
            Quiz quizInfo = quiz.get();
            questionIds = quizInfo.getQuestionIds();
        }

        for (Integer questionId : questionIds) {
            QuestionDTO questionDTO = interfaceQuiz.getQuestionFromId(questionId);
            if (questionDTO != null) {  // Handle possible nulls from the Feign client
                questionDTOS.add(questionDTO);
            }
        }

        return ResponseEntity.ok(questionDTOS);  // Corrected return statement
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return interfaceQuiz.getScoreInfo(responses);
    }
}
