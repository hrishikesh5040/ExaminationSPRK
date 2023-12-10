package com.sprk.ExaminationPortal.dto.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuesRequest {

    @NotBlank
    private String quesType;

    @NotBlank
    private String question;
    

    private String mcqOption1;
    

    private String mcqOption2;
    

    private String mcqOption3;
    

    private String mcqOption4;
    
    @NotBlank
    private String answer;
    
    @NotBlank
    private String techName;
}
