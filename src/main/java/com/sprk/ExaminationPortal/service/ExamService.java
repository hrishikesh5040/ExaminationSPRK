package com.sprk.ExaminationPortal.service;

import com.sprk.ExaminationPortal.dto.payload.LoginRequest;
import com.sprk.ExaminationPortal.dto.payload.AddQuesRequest;
import com.sprk.ExaminationPortal.dto.payload.SendmailRequest;
import com.sprk.ExaminationPortal.dto.response.ApiResponse;

public interface ExamService {
	ApiResponse<?> addquestion(AddQuesRequest qRequest);

	ApiResponse<?> getAllQuestion(String technology);

	ApiResponse<?> userLogin(LoginRequest loginRequest);

	ApiResponse<?> sendMail(SendmailRequest sendmailRequest);

	ApiResponse<?> startexam(String email);
}
