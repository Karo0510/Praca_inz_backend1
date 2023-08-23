package com.example.bhp.controller;

import com.example.bhp.converter.StringToBooleanConverter;
import com.example.bhp.converter.StringToPriorityConverter;
import com.example.bhp.createViews.AccidentDetails;
import com.example.bhp.entity.RegistryOfAccidents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ComponentScan(basePackages = {"com.example.bhp"})
class RegisterOfAccidentsControllerTest {

    @Autowired
    RegisterOfAccidentsController reg;

    @Test
    void fetchEmployees() {
    }

    @Test
    void findAllByBranch() {
    }

    @Test
    void findAllByAccidentId() {
    }

    @Test
    void findByData() {
    }

    @Test
    void findAllByAccidentByIdAndBranch() {
    }

    @Test
    @Rollback
    void addAccidentTest() {

        ArrayList<Long> id = new ArrayList<>();
        id.add(1L);

        AccidentDetails acc =  new AccidentDetails();
        acc.setDepartment(9);
        acc.setProtocole("03_2023");
        acc.setDate(LocalDate.of(2023, 04, 01));
        acc.setPlace("bla bla");

        acc.setAccident(true);
        acc.setAccident_priority(RegistryOfAccidents.Accident_priority.LIGHT);
        acc.setIdEmployees(id);

        ArrayList<AccidentDetails> list = new ArrayList<>();
        list.add(acc);

        ResponseEntity<String> ans = reg.addAccident(list);

        //System.out.println(ans.getBody());

        //assertTrue(ans.getStatusCode() == HttpStatus.CREATED);
    }
}