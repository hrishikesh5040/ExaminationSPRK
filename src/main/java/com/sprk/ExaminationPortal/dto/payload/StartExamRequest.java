package com.sprk.ExaminationPortal.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartExamRequest {

    private String email;

    private String firstName;

    private String lastName;



}
