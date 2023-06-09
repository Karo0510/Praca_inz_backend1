package com.example.bhp.controller;


import com.example.bhp.entity.OccupationalRiskAssesment;
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
    public List<OccupationalRiskAssesment> fetchEmployees() {
        return register.findAll();
    }

    @PostMapping("/risk")
    public OccupationalRiskAssesment addRisk(@RequestBody OccupationalRiskAssesment reg) {
        return register.save(reg);
    }

}
