package com.services.testtemplateservice.dto;


import com.services.testtemplateservice.model.Question;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestResponse {
    private int id;
    private String title;
    private String desc;
    private LocalDateTime creation_date;
    private int company_id;
    private List<Question> questions;
}
