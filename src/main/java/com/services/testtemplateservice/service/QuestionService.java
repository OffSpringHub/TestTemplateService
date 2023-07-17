package com.services.testtemplateservice.service;

import com.services.testtemplateservice.dto.QuestionRequest;
import com.services.testtemplateservice.model.Category;
import com.services.testtemplateservice.model.Options;
import com.services.testtemplateservice.model.Question;
import com.services.testtemplateservice.model.QuestionType;
import com.services.testtemplateservice.repository.CategoryRepository;
import com.services.testtemplateservice.repository.OptionsRepository;
import com.services.testtemplateservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionsRepository optionsRepository;

    public Question addCategoryToQuestion(int category_id, QuestionRequest questionRequest){
        Category category = categoryRepository.findById(category_id).orElseThrow(()-> new IllegalArgumentException("Invalid Category Id"));
        questionRequest.setCategory(category);
        Question question = mapToQuestion(questionRequest);
        return questionRepository.save(question);
    }

    private Question mapToQuestion(QuestionRequest questionRequest) {
        return Question.builder()
                .questionType(questionRequest.getQuestionType())
                .name(questionRequest.getName())
                .score(questionRequest.getScore())
                .options(questionRequest.getOptions())
                .difficulty(questionRequest.getDifficulty())
                .estimated_time(questionRequest.getEstimated_time())
                .category(questionRequest.getCategory())
                .build();
    }

    public Question findQuestionById(int questionId) {
        return questionRepository.findById(questionId).orElseThrow(()->new IllegalArgumentException("Invalid Question Id"));
    }

    public void saveQuestion(QuestionRequest questionRequest) {
        Question question = mapToQuestion(questionRequest);
        questionRepository.save(question);
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
}
