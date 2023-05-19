package com.example.bhp.repository;

import com.example.bhp.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobPosition, Long> {
}
