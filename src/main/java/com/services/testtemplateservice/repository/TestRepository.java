package com.services.testtemplateservice.repository;

import com.services.testtemplateservice.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Integer> {
    @Query("SELECT test FROM Test test WHERE test.company_id= :company_id")
    public List<Test> getTestsByCompany_id(@Param("company_id") int company_id);
}
