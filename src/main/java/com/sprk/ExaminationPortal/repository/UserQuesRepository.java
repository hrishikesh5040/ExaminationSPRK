package com.sprk.ExaminationPortal.repository;

import com.sprk.ExaminationPortal.Entity.UserQuesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuesRepository extends JpaRepository<UserQuesEntity, Long> {
    List<UserQuesEntity> findByEmailId(String email);
}
