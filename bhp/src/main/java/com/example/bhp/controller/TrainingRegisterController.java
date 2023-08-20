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
    public List<TrainingDetails> fetchTrainings()
    {
        List<TrainingRegister> list =  TrainingInfo.trainings();

        List<TrainingDetails> ans = new ArrayList<>();

        for (int i = 0; i<list.size(); i++)
        {
            TrainingDetails details = new TrainingDetails();
            ans.add(details.setData(list.get(i)));
        }

        return ans;
    }

    /*@GetMapping("/training_register/$id={id}")
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
    }*/

    @GetMapping("/training_register/$id={id}")
    public TrainingDetails fetchTrainingsDetail(@PathVariable(value="id") Long number)
    {
        TrainingRegister ans = TrainingInfo.training(number);

        System.out.println(ans);

        TrainingDetails detail = new TrainingDetails();
        detail.setData(ans);

        return detail;
    }



    @PostMapping("/training_register")
    public TrainingRegister addTraining(@RequestBody TrainingRegister reg) {
        return register.save(reg);
    }
}
