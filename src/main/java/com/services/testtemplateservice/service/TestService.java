package com.services.testtemplateservice.service;


import com.services.testtemplateservice.dto.TestRequest;
import com.services.testtemplateservice.dto.TestResponse;
import com.services.testtemplateservice.model.Question;
import com.services.testtemplateservice.model.Test;
import com.services.testtemplateservice.repository.QuestionRepository;
import com.services.testtemplateservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public List<TestResponse> findAll() {
        List<Test> tests = testRepository.findAll();
        return tests.stream().map(this::mapToTestResponse).toList();
    }

    private TestResponse mapToTestResponse(Test test){
        return TestResponse.builder()
                .title(test.getTitle())
                .creation_date(test.getCreation_date())
                .desc(test.getDescription())
                .company_id(test.getCompany_id())
                .id(test.getId())
                .questions(test.getQuestions())
                .build();
    }

    public void createTest(TestRequest testRequest) {
        Test test = Test.builder()
                .title(testRequest.getTitle())
                .description(testRequest.getDesc())
                .creation_date(testRequest.getCreation_date())
                .company_id(testRequest.getCompany_id())
                .build();
        testRepository.save(test);
    }

    private void addQuestionsToTest(List<Integer> ids, Test test) {
        List<Question> questions = questionRepository.findAllById(ids);
        test.setQuestions(questions);
        testRepository.save(test);
    }

    public TestResponse getTestById(int id) {
        Test test = testRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Test Id"));
        return mapToTestResponse(test);
    }

    public List<TestResponse> getTestByCompany(int companyId) {
        List<Test> tests = testRepository.getTestsByCompany_id(companyId);
        return tests.stream().map(this::mapToTestResponse).toList();
    }

    public void deleteTest(TestResponse testResponse) {
        Test test = Test.builder()
                .title(testResponse.getTitle())
                .creation_date(testResponse.getCreation_date())
                .questions(testResponse.getQuestions())
                .description(testResponse.getDesc())
                .company_id(testResponse.getCompany_id())
                .build();
        testRepository.delete(test);
    }

    public Test findById(int id){
        return testRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Test Id"));
    }

    public void updateTest(TestResponse testResponse, TestRequest testRequest) {
        Test test = testRepository.findById(testResponse.getId()).orElseThrow(()-> new IllegalArgumentException("Invalid Test Id"));

        test.setQuestions(testRequest.getQuestions());
        test.setDescription(testRequest.getDesc());
        test.setTitle(testRequest.getTitle());
        test.setCompany_id(testRequest.getCompany_id());
        test.setCreation_date(testRequest.getCreation_date());

        testRepository.save(test);
    }

    public void addTest(Test test) {
        testRepository.save(test);
    }
}
