package com.sprk.ExaminationPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprk.ExaminationPortal.Entity.QuesEntity;

import java.util.List;

@Repository
public interface QuesRepository extends JpaRepository<QuesEntity, Long>{

    List<QuesEntity> findByTechnology(String technology);
}
