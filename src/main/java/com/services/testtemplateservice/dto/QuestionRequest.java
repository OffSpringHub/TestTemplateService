package com.services.testtemplateservice.dto;

import com.services.testtemplateservice.model.Category;
import com.services.testtemplateservice.model.Difficulty;
import com.services.testtemplateservice.model.Options;
import com.services.testtemplateservice.model.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequest {
    private String name;
    private QuestionType questionType;
    private Difficulty difficulty;
    private int estimated_time;
    private float score;
    private Category category;
    private List<Options> options;
}
