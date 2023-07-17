package com.services.testtemplateservice.controller;


import com.services.testtemplateservice.dto.OptionsRequest;
import com.services.testtemplateservice.model.Options;
import com.services.testtemplateservice.model.Question;
import com.services.testtemplateservice.service.OptionsService;
import com.services.testtemplateservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionsController {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private QuestionService questionService;

    @PostMapping("/{questionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOptionForQuestion(@PathVariable int questionId,@RequestBody List<OptionsRequest> option){
        Question question=questionService.findQuestionById(questionId);
        if(question != null){
            optionsService.createOptionForQuestion(option,question);
        }
    }

    @DeleteMapping("/{optionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOption(@PathVariable int optionId){
        Options option=optionsService.findById(optionId);
        if(option != null){
            optionsService.deleteOption(option);
        }
    }

    @PutMapping("/{optionId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOption(@PathVariable int optionId,@RequestBody OptionsRequest optionsRequest){
        Options searchedOption = optionsService.findById(optionId);
        if(searchedOption != null){
            searchedOption.setName(optionsRequest.getName());
            searchedOption.setId(optionId);
            searchedOption.setCorrect(optionsRequest.isCorrect());

            optionsService.addOption(searchedOption);
        }
    }

}
