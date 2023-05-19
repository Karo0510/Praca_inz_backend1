package com.example.bhp.controller;

import com.example.bhp.entity.TrainingRegister;
import com.example.bhp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class TrainingRegisterController {

    @Autowired
    private TrainingRepository register;

    @GetMapping("/training_register")
    public List<TrainingRegister> fetchTrainings()
    {
        return register.findAll();
    }

    @PostMapping("/training_register")
    public TrainingRegister addTraining(@RequestBody TrainingRegister reg) {
        return register.save(reg);
    }
}
