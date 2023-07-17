package com.services.testtemplateservice.repository;

import com.services.testtemplateservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
