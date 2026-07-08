package com.ms.quiz_service.controller;

import com.ms.quiz_service.DTO.QuestionDTO;
import com.ms.quiz_service.model.Response;
import com.ms.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
       return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestion(@RequestParam Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public  ResponseEntity<Integer> submitQuiz(@RequestParam Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
