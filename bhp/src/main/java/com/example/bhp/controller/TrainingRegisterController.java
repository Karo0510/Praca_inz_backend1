package com.example.bhp.controller;

import com.example.bhp.createViews.TrainingDetails;
import com.example.bhp.dao.TrainingInfo;
import com.example.bhp.entity.TrainingRegister;
import com.example.bhp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class TrainingRegisterController {

    @Autowired
    private TrainingRepository register;

    @GetMapping("/training_register")
    public List<TrainingRegister> fetchTrainings()
    {
        return TrainingInfo.trainings();
    }

    @GetMapping("/training_register/$id={id}")
    public List<TrainingDetails> fetchTrainingsDetail()
    {
        List<TrainingDetails> details = new ArrayList<>();
        List<TrainingRegister> reg = TrainingInfo.trainings();

        for (TrainingRegister r: reg)
        {
            TrainingDetails detail = new TrainingDetails();
            details.add(detail.setData(r));
        }

        return details;
    }



    @PostMapping("/training_register")
    public TrainingRegister addTraining(@RequestBody TrainingRegister reg) {
        return register.save(reg);
    }
}
