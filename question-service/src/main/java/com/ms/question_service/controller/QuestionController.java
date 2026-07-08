package com.ms.question_service.controller;

import com.ms.question_service.DTO.QuestionDTO;
import com.ms.question_service.mapper.QuestionMapper;
import com.ms.question_service.model.Question;
import com.ms.question_service.model.Response;
import com.ms.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    Environment environment;

    @GetMapping("test")
    public String getWelCome() {
        return "Welcome";
    }

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getQuestions() {
        System.out.println(environment.getProperty("local.server.port"));
        return ResponseEntity.ok(questionService.getQustions());
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsForCategory(@RequestParam String category) {
        return ResponseEntity.ok(questionService.getQustionByCategory(category));
    }

    @GetMapping("category/{category}/{noofque}")
    public ResponseEntity<List<Question>> getQuestion(@PathVariable String category, @PathVariable Integer noofque) {
        return ResponseEntity.ok(questionService.getQuestionList(category, noofque));
    }


    @PostMapping("addquestion")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }


    @GetMapping("score")
    public ResponseEntity<Integer> getScore(@RequestParam List<Integer> IdNumbers) {
        int score = 0;
        for (Integer id : IdNumbers) {
            Optional<Question> question = questionService.getQuestion(id);
            if (question.isPresent()) {
                Question que = question.get();
                Optional<Question> answer = questionService.getAnswer(que.getRightAnswer());
                if (answer.isPresent()) {
                    score++;
                }
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PostMapping("question")
    public Question getQuestionFromId(@RequestParam Integer id) {
        return questionService.getQuestionFromId(id);
    }

    //required
    @PostMapping("question-list")
    public ResponseEntity<List<QuestionDTO>> getQuestionById(@RequestParam List<Integer> Ids) {
        Question que = null;
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Integer id : Ids) {
            Optional<Question> question = questionService.getQuestion(id);
            if (question.isPresent()) {
                que = question.get();
                QuestionDTO dto = questionMapper.toDTO(que);
                questionDTOs.add(dto);
            }
        }
        return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
    }


    //required
    @PostMapping("get-score")
    public Integer getScoreInfo(@RequestBody List<Response> responses) {
        return questionService.getSocre(responses);
    }

    //required
    @PostMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer noofque) {
        List<Integer> questionIdList = questionService.getQuestionIdList(category, noofque);
        return new ResponseEntity<>(questionIdList, HttpStatus.OK);
    }
}
