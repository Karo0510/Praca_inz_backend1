package com.example.bhp.repository;

import com.example.bhp.entity.OccupationalRiskAssesment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskRepository extends JpaRepository<OccupationalRiskAssesment, Long> {
}
