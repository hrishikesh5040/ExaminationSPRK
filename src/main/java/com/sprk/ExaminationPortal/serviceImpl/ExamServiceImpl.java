package com.sprk.ExaminationPortal.serviceImpl;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sprk.ExaminationPortal.Entity.UserEntity;
import com.sprk.ExaminationPortal.Entity.UserQuesEntity;
import com.sprk.ExaminationPortal.Exception.InvalidDataException;
import com.sprk.ExaminationPortal.dto.payload.LoginRequest;
import com.sprk.ExaminationPortal.dto.payload.SendmailRequest;
import com.sprk.ExaminationPortal.dto.response.ApiResponse;
import com.sprk.ExaminationPortal.dto.response.QResponse;
import com.sprk.ExaminationPortal.dto.response.UserQuesResponse;
import com.sprk.ExaminationPortal.repository.UserQuesRepository;
import com.sprk.ExaminationPortal.repository.UserRepository;
import com.sprk.ExaminationPortal.service.JwtUtils;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sprk.ExaminationPortal.Entity.QuesEntity;
import com.sprk.ExaminationPortal.dto.payload.AddQuesRequest;
import com.sprk.ExaminationPortal.repository.QuesRepository;
import com.sprk.ExaminationPortal.service.ExamService;


@Service
public class ExamServiceImpl implements ExamService {

    private final QuesRepository quesRepository;

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private final UserQuesRepository userQuesRepository;

    private final JwtUtils jwtUtils;

    @Autowired
    public ExamServiceImpl(
            QuesRepository quesRepository,
            UserRepository userRepository,
            JavaMailSender javaMailSender,
            UserQuesRepository userQuesRepository,
            JwtUtils jwtUtils
    ){
            this.quesRepository = quesRepository;
            this.userRepository = userRepository;
            this.javaMailSender = javaMailSender;
            this.userQuesRepository = userQuesRepository;
            this.jwtUtils = jwtUtils;
    }

    @Value("${spring.mail.username}")
    private String sender;


    @PostConstruct
    public void init() {
        if(0 == userRepository.count()) {
            List<UserEntity> entities = List.of(
                    UserEntity.builder().id(1L).firstName("Rahul")
                    .lastName("Dev")
                    .emailId("r.dev@gmail.com")
                    .password("abc@123")
                    .build(),

                    UserEntity.builder()
                            .id(2L)
                            .firstName("Akash")
                            .lastName("Bose")
                            .emailId("a.bose@gmail.com")
                            .password("abc@123")
                            .build()
            );
            userRepository.saveAll(entities);
        }
  }

        @Override
        public ApiResponse<?> addquestion (AddQuesRequest qRequest){
            try {

                QuesEntity quesEntity = new QuesEntity();

                if(qRequest.getQuesType().equals("MCQ"))
                {
                    quesEntity = QuesEntity.builder()
                            .question(qRequest.getQuestion())
                            .option1(qRequest.getMcqOption1())
                            .option2(qRequest.getMcqOption2())
                            .option3(qRequest.getMcqOption3())
                            .option4(qRequest.getMcqOption4())
                            .qType(qRequest.getQuesType())
                            .answer(qRequest.getAnswer())
                            .technology(qRequest.getTechName())
                            .build();
                }

                if(qRequest.getQuestion().equals("Coding")){
                    quesEntity = QuesEntity.builder()
                            .question(qRequest.getQuestion())
                            .option1("-")
                            .option2("-")
                            .option3("-")
                            .option4("-")
                            .qType(qRequest.getQuesType())
                            .answer(qRequest.getAnswer())
                            .technology(qRequest.getTechName())
                            .build();
                }

                if(qRequest.getQuestion().equals("Fill")){
                    quesEntity = QuesEntity.builder()
                            .question(qRequest.getQuestion())
                            .option1("-")
                            .option2("-")
                            .option3("-")
                            .option4("-")
                            .qType(qRequest.getQuesType())
                            .answer(qRequest.getAnswer())
                            .technology(qRequest.getTechName())
                            .build();
                }

                quesRepository.save(quesEntity);

            } catch (Exception exception) {
                throw new RuntimeException();
            }
            return ApiResponse.builder().message("Added Succesfully to MCQ Question Bank").build();
        }

        @Override
        public ApiResponse<?> getAllQuestion (String tech){

            List<QuesEntity> quesEntityList = quesRepository.findByTechnology(tech);

            List<QResponse> qResponses = new ArrayList<>();

            for (QuesEntity quesEntity : quesEntityList) {

                QResponse qresponse = QResponse.builder()
                        .id(quesEntity.getId())
                        .mcqQuestion(quesEntity.getQuestion())
                        .mcqOption1(quesEntity.getOption1())
                        .mcqOption2(quesEntity.getOption2())
                        .mcqOption3(quesEntity.getOption3())
                        .mcqOption4(quesEntity.getOption4())
                        .qType(quesEntity.getQType())
                        .build();

                qResponses.add(qresponse);
            }
            return ApiResponse.builder().data(qResponses).build();

        }

        @Override
        public ApiResponse<?> userLogin (LoginRequest loginRequest){

            UserEntity user = userRepository.findByEmailId(loginRequest.getEmail());

            if (null == user) {
                throw new InvalidDataException("");
            }

            if(!user.getPassword().equals(loginRequest.getPassword()))
                throw new RuntimeException();

            String firstname = user.getFirstName();

            String lastname = user.getLastName();

            String fullname = firstname + " " + lastname;

            return ApiResponse.builder().message("Login Succesfull").data(fullname).build();

        }

    @Override
    public ApiResponse<?> sendMail(SendmailRequest sendmailRequest) {

        if(sendmailRequest.getQid().isEmpty())
            throw new RuntimeException();

        List<Long> questionList = sendmailRequest.getQid();

        for(Long id : questionList)
        {
            UserQuesEntity userQuesEntity = UserQuesEntity.builder()
                    .emailId(sendmailRequest.getEmailId())
                    .questionId(id)
                    .build();

            userQuesRepository.save(userQuesEntity);
        }


        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sendmailRequest.getEmailId());
            mimeMessageHelper.setText("Click on the link to proceed\n");
            mimeMessageHelper.setSubject(
                    "Link for Exam");

            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            //return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            //return "Error while sending mail!!!";
        }

        return ApiResponse.builder().message("Link for exam sent successfully on the given mail id").build();
    }

    @Override
    public ApiResponse<?> startexam(String email) {

//        String token = jwtUtils.generateToken(email, Instant.now().toEpochMilli());

        List<UserQuesEntity> userQuesEntity = userQuesRepository.findByEmailId(email);

        List<UserQuesResponse> userQuesResponses = new ArrayList<>();

        if(userQuesEntity.isEmpty())
            throw new RuntimeException();

        for(UserQuesEntity userQEntity: userQuesEntity){

            Optional<QuesEntity> quesEntity = quesRepository.findById(userQEntity.getId());

            if(quesEntity.isEmpty())
                throw new RuntimeException();

           UserQuesResponse userQuesResponse = UserQuesResponse.builder()
                    .id(quesEntity.get().getId())
                    .question(quesEntity.get().getQuestion())
                    .option1(quesEntity.get().getOption1())
                    .option2(quesEntity.get().getOption2())
                    .option3(quesEntity.get().getOption3())
                    .option4(quesEntity.get().getOption4())
                   // .answer(quesEntity.get().getAnswer())
                    .build();

            userQuesResponses.add(userQuesResponse);
        }

        return ApiResponse.builder().data(userQuesResponses).build();
    }


}