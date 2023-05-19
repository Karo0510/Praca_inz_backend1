package com.example.bhp.controller;

import com.example.bhp.entity.JobPosition;
import com.example.bhp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class JobPositionController {

    @Autowired
    private JobRepository register;

    @GetMapping("/jobs")
    public List<JobPosition> fetchEmployees() {
        return register.findAll();
    }

    @PostMapping("/jobs")
    public JobPosition addJob(@RequestBody JobPosition reg) {
        return register.save(reg);
    }

}
