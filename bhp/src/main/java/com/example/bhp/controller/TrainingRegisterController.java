package com.example.bhp.controller;

import com.example.bhp.createViews.TrainingDetails;
import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.dao.TrainingInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.TrainingRegister;
import com.example.bhp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ResponseEntity<String> addTraining(@RequestBody List<TrainingDetails> reg) {

        if (reg.isEmpty()) {
            return new ResponseEntity<>("Tabela jest pusta", HttpStatus.NOT_ACCEPTABLE);
        }

        String text = "";

        for (Integer i = 0; i < reg.size(); i++) {
            List<Employees> emp = reg.get(i).getIdEmployees().stream().map(j -> EmployeeInfo.findEmployeeById(j)).collect(Collectors.toList());

            TrainingRegister ans = TrainingRegister.builder()
                    .first_date(reg.get(i).getFirstTrainingDate())
                    .date_exam(reg.get(i).examTrainingDate)
                    .build();

            TrainingRegister saved = TrainingInfo.addTraining(reg.get(i), emp);

            if (saved == null) {
                Integer l = i + 1;
                text += "Problem z zapisem treningu nr. " + l.toString() + "\n";
            } else {
                Integer l = i + 1;
                text += "Zapisany nr. " + l.toString() + "\n";
            }
        }

        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
