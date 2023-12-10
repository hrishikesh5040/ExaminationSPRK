package com.sprk.ExaminationPortal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mcq_qb")
public class QuesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_id")
    private Long id;

    @Column(name = "ques", nullable = false, unique = true)
    private String question;

    @Column(name = "option_1", nullable = false)
    private String option1;

    @Column(name = "option_2", nullable = false)
    private String option2;

    @Column(name = "option_3", nullable = false)
    private String option3;

    @Column(name = "option_4", nullable = false)
    private String option4;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "tech_name", nullable = false)
    private String technology;

    @Column(name = "q_type", nullable = false)
    private String qType;


    //    @Column(name = "created_at")
//    private Instant createdAt;
//
//    @Column(name = "updated_at")
//    private Instant updateAt;
//
//    @Column(name = "status")
//    private boolean status = true;
//
//    @Column(name = "created_by")
//    private Long createdBy;
//
//    @Column(name = "updated_by")
//    private Long updatedBy;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.REMOVE})
//    @JoinColumn(name = "tech_id", nullable = false)
}
