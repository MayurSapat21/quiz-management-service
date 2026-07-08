package com.ms.quiz_service.feign;

import com.ms.quiz_service.DTO.QuestionDTO;
import com.ms.quiz_service.model.Response;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {

    //required
    @PostMapping("/question/api/question-list")
    public ResponseEntity<List<QuestionDTO>> getQuestionById(@RequestParam List<Integer> Ids) ;

    @PostMapping("/question/api/question")
    public QuestionDTO getQuestionFromId(@RequestParam Integer id);

    //required
    @PostMapping("/question/api/get-score")
    public ResponseEntity<Integer> getScoreInfo(@RequestBody List<Response> responses);

    //required
    @PostMapping("/question/api/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer noofque);
}
