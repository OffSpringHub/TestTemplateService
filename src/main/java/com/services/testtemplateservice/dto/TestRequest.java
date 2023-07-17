package com.services.testtemplateservice.dto;

import com.services.testtemplateservice.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {
    private String title;
    private String desc;
    private LocalDateTime creation_date;
    private int company_id;
    private List<Question> questions;
}
