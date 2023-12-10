package com.sprk.ExaminationPortal.dto.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendmailRequest {

    @NotBlank
    private List<Long> qid;

    @NotBlank
    private String emailId;




}
