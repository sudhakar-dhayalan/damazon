package com.damazon.backend.repo;

import com.damazon.backend.model.AssessmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentDetailRepo extends JpaRepository<AssessmentDetail, Integer> {
    List<AssessmentDetail> findByEmail(String email);
}
