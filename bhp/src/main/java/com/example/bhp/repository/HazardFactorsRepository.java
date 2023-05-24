package com.example.bhp.repository;

import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HazardFactorsRepository extends JpaRepository<HazardFactors, Long> {
}
