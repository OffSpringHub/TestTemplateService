package com.services.testtemplateservice.controller;

import com.services.testtemplateservice.dto.TestRequest;
import com.services.testtemplateservice.dto.TestResponse;
import com.services.testtemplateservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_template")
public class TestController {
    @Autowired
    private TestService testService;


    @GetMapping("/{id}")
    public ResponseEntity<TestResponse> getTestById(@PathVariable int id){
        TestResponse testResponse = testService.getTestById(id);
        if(testResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(testResponse);
    }

    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<TestResponse>> getAllTestByCompany(@PathVariable("company_id") int company_id){
        List<TestResponse> testResponses = testService.getTestByCompany(company_id);
        if(testResponses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(testResponses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTest(@PathVariable("id") int id){
        TestResponse test = testService.getTestById(id);
        testService.deleteTest(test);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTest(@RequestBody TestRequest testRequest){
        testService.createTest(testRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTest(@RequestBody TestRequest testRequest,@PathVariable("id") int id){
        TestResponse testResponse = testService.getTestById(id);
        if(testResponse != null ){
            testService.updateTest(testResponse,testRequest);
        }
    }
}
