package com.ms.question_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDTO {
    private Integer id;
    private String QuestionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
