package com.example.bhp.controller;


import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class RiskController {

    @Autowired
    private RiskRepository register;

    @GetMapping("/risk")
    public List<RiskAssessment> fetchEmployees() {
        return register.findAll();
    }

    @PostMapping("/risk")
    public RiskAssessment addRisk(@RequestBody RiskAssessment reg) {
        return register.save(reg);
    }

}
