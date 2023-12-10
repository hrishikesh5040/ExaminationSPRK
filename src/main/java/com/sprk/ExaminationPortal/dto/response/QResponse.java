package com.sprk.ExaminationPortal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QResponse {

    private Long id;

    private String mcqQuestion;

    private String mcqOption1;

    private String mcqOption2;

    private String mcqOption3;

    private String mcqOption4;

    private String qType;
}
