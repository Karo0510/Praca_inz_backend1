package com.example.bhp.repository;

import com.example.bhp.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskRepository extends JpaRepository<RiskAssessment, Long> {
}
