package com.example.bhp.controller;


import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class RegisterOfAccidentsController {

    @Autowired
    private RegisterOfAccidentsRepository register;

    @GetMapping("/register_of_accidents")
    public List<RegistryOfAccidents> fetchEmployees() {
        return register.findAll();
    }

    @PostMapping("/register_of_accidents")
    public RegistryOfAccidents addAccident(@RequestBody RegistryOfAccidents reg) {
        return register.save(reg);
    }

}
