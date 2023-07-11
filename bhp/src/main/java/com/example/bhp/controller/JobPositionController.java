package com.example.bhp.controller;

import com.example.bhp.createViews.JobDetails;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081/")
public class JobPositionController {

    @Autowired
    private JobRepository register;

    @GetMapping("/jobs")
    public List<JobPosition> fetchEmployees() {
        return register.findAll();
    }

    @PostMapping("/jobs")
    public ResponseEntity addJob(@RequestBody JobDetails job) {

        return ResponseEntity.ok("ok");
    }

}
