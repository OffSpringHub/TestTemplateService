package com.services.testtemplateservice.dto;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionsRequest {
    private String name;
    private boolean isCorrect;
}
