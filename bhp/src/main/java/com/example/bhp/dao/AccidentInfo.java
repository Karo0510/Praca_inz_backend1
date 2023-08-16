package com.example.bhp.dao;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class AccidentInfo {

    @Autowired
    private RegisterOfAccidentsRepository register;


}
