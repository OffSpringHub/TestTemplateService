package com.services.testtemplateservice.repository;

import com.services.testtemplateservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
