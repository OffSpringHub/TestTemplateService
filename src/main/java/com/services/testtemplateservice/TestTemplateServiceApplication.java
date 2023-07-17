package com.services.testtemplateservice;

import com.services.testtemplateservice.dto.TestRequest;
import com.services.testtemplateservice.model.*;
import com.services.testtemplateservice.repository.CategoryRepository;
import com.services.testtemplateservice.repository.OptionsRepository;
import com.services.testtemplateservice.repository.QuestionRepository;
import com.services.testtemplateservice.repository.TestRepository;
import com.services.testtemplateservice.service.QuestionService;
import com.services.testtemplateservice.service.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestTemplateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTemplateServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadCategories(CategoryRepository categoryRepository, QuestionService questionService, OptionsRepository optionsRepository, TestService testService){
        return args -> {
            List<Category> categories = new ArrayList<>();

            categories.add(new Category(0,"React(Advanced)"));
            categories.add(new Category(0,"React(Intermediate)"));
            categories.add(new Category(0,"React(Beginner)"));
            categories.add(new Category(0,"SpringBoot(Advanced)"));
            categories.add(new Category(0,"SpringBoot(Intermediate)"));
            categories.add(new Category(0,"SpringBoot(Beginner)"));
            categories.add(new Category(0,"DevOps(Advanced)"));
            categories.add(new Category(0,"DevOps(Beginner)"));
            categories.add(new Category(0,"Docker(Advanced)"));
            categories.add(new Category(0,"Docker(Beginner)"));
            categoryRepository.saveAll(categories);


            TestRequest testRequest = new TestRequest("Test 1","Description for Test 1",LocalDateTime.now(),1,null);
            TestRequest testRequest1 = new TestRequest("Test 2","Description for Test 2",LocalDateTime.now(),1,null);
            TestRequest testRequest2 = new TestRequest("Test 3","Description for Test 3",LocalDateTime.now(),2,null);
            TestRequest testRequest3 = new TestRequest("Test 4","Description for Test 4",LocalDateTime.now(),3,null);
            testService.createTest(testRequest1);
            testService.createTest(testRequest2);
            testService.createTest(testRequest3);
            testService.createTest(testRequest);

        };
    }

    private Integer  mapToIdsQuestion(Question question) {
        return question.getId();
    }

    private Integer mapToIds(Options options) {
        return options.getId();
    }

}
