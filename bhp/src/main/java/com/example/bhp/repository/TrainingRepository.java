package com.example.bhp.repository;

import com.example.bhp.entity.TrainingRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingRegister, Long> {
}
