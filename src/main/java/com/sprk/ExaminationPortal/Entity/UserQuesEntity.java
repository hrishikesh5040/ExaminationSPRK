package com.sprk.ExaminationPortal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "userquestions")
public class UserQuesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uq_id")
    private Long id;

    @Column(name = "uq_mail")
    private String emailId;

    @Column(name = "q_id")
    private Long questionId;

}
