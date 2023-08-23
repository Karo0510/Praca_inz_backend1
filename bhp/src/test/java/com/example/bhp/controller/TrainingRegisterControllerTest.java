package com.example.bhp.controller;

import com.example.bhp.createViews.TrainingDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = {"com.example.bhp"})
class TrainingRegisterControllerTest {

    @Autowired
    TrainingRegisterController controller;

    @Test
    void addTraining() {

        ArrayList<Long> id = new ArrayList<>();
        id.add(1L);
        id.add(2L);
        id.add(3L);

        TrainingDetails details = new TrainingDetails();
        details.setExamTrainingDate(LocalDate.of(2022, 01, 03));
        details.setFirstTrainingDate(LocalDate.of(2022, 01, 03));
        details.setIdEmployees(id);

        ArrayList<TrainingDetails> reg = new ArrayList<>();
        reg.add(details);

        controller.addTraining(reg);

        assertNotEquals(1, 2);




    }
}