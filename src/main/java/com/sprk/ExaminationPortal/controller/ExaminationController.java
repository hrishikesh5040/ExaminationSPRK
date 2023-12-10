package com.sprk.ExaminationPortal.controller;

import com.sprk.ExaminationPortal.dto.payload.LoginRequest;
import com.sprk.ExaminationPortal.dto.payload.SendmailRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprk.ExaminationPortal.dto.payload.AddQuesRequest;
import com.sprk.ExaminationPortal.dto.response.ApiResponse;
import com.sprk.ExaminationPortal.service.ExamService;

@RestController
@RequestMapping(path = "/api/exam")
public class ExaminationController {

	private final ExamService examService;

	@Autowired
	public ExaminationController(ExamService examService){
		this.examService = examService;
	}
	
	@PostMapping(path = "/addquestion")
	public ResponseEntity<?> addQuestion(
			@RequestBody @Valid AddQuesRequest qRequest
	){
		ApiResponse<?> response = examService.addquestion(qRequest);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping(path = "/getquestionlist")
	public ResponseEntity<?> getListOfMcq(
			@RequestParam String tech
	) {

		ApiResponse<?> response = examService.getAllQuestion(tech);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}


	@PostMapping(path = "/login")
	public ResponseEntity<?> login(
			@RequestBody @Valid LoginRequest loginRequest
	){

		ApiResponse<?> response = examService.userLogin(loginRequest);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);

	}


	@PostMapping(path = "/sendLink")
	public ResponseEntity<?> sendLink(
			@RequestBody SendmailRequest sendmailRequest
	){
		ApiResponse<?> apiResponse = examService.sendMail(sendmailRequest);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(apiResponse);
	}

	@PostMapping(path = "/startexam")
	public ResponseEntity<?> startExam(
			@RequestParam String email
	){
		ApiResponse<?> apiResponse = examService.startexam(email);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(apiResponse);
	}

}
