package com.sprk.ExaminationPortal.repository;

import com.sprk.ExaminationPortal.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailId(String email);
}
