package com.services.testtemplateservice.controller;


import com.services.testtemplateservice.dto.QuestionRequest;
import com.services.testtemplateservice.model.Question;
import com.services.testtemplateservice.model.Test;
import com.services.testtemplateservice.service.QuestionService;
import com.services.testtemplateservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestService testService;

    @PostMapping("/{testId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestionToTest(@RequestBody QuestionRequest question, @PathVariable int testId, @RequestParam int categoryId){
        Question savedQuestion = questionService.addCategoryToQuestion(categoryId,question);
        Test test = testService.findById(testId);
        if(savedQuestion != null && test != null){
            test.getQuestions().add(savedQuestion);
            testService.addTest(test);
        }
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@PathVariable("questionId") int questionId){
        Question question = questionService.findQuestionById(questionId);
        if(question != null){
            questionService.deleteQuestion(question);
        }
    }

    @PutMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuestion(@PathVariable("questionId") int questionId,@RequestBody QuestionRequest questionRequest){
        Question searchedQuestion = questionService.findQuestionById(questionId);
        if(searchedQuestion != null ){
            searchedQuestion.setOptions(questionRequest.getOptions());
            searchedQuestion.setCategory(questionRequest.getCategory());
            searchedQuestion.setQuestionType(questionRequest.getQuestionType());
            searchedQuestion.setDifficulty(questionRequest.getDifficulty());
            searchedQuestion.setEstimated_time(questionRequest.getEstimated_time());
            searchedQuestion.setScore(questionRequest.getScore());
            searchedQuestion.setName(questionRequest.getName());

            questionService.saveQuestion(questionRequest);
        }
    }

}
