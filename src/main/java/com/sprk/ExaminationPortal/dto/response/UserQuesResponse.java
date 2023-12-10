package com.sprk.ExaminationPortal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuesResponse {


    private Long id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

   // private String answer;

}
