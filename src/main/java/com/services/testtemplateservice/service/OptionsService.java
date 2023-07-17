package com.services.testtemplateservice.service;

import com.services.testtemplateservice.dto.OptionsRequest;
import com.services.testtemplateservice.model.Options;
import com.services.testtemplateservice.model.Question;
import com.services.testtemplateservice.repository.OptionsRepository;
import com.services.testtemplateservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsService {
    @Autowired
    private OptionsRepository optionsRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public Options findById(int optionId) {
        return optionsRepository.findById(optionId).orElseThrow(()->new IllegalArgumentException("Invalid Option Id"));
    }

    public void deleteOption(Options option) {
        optionsRepository.delete(option);
    }

    public void addOption(Options searchedOption) {
        optionsRepository.save(searchedOption);
    }

    public void createOptionForQuestion(List<OptionsRequest> option, Question question) {
        question.setOptions(option.stream().map(this::mapToOptions).toList());
        questionRepository.save(question);
    }

    private Options mapToOptions(OptionsRequest optionsRequest) {
        return Options.builder()
                .name(optionsRequest.getName())
                .isCorrect(optionsRequest.isCorrect())
                .build();
    }
}
