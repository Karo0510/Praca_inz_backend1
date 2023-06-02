package com.example.bhp.controller;


import com.example.bhp.dao.AccidentInfo;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class RegisterOfAccidentsController {

    @Autowired
    private RegisterOfAccidentsRepository register;


    @GetMapping("/register_of_accidents")
    public List<RegistryOfAccidents> fetchEmployees()
    {
        return register.findAll();
    }

    @GetMapping("/register_of_accidents/$branch={branch}")
    public List<RegistryOfAccidents> findAllByBranch(@PathVariable (value="branch") Integer branch)
    {
        return register.findByKeyResponsibleBranch(branch);
    }

    @GetMapping("/register_of_accidents/$id={id}")
    public List<RegistryOfAccidents> findAllByAccidentId(@PathVariable (value="id") Long id)
    {
        return register.findByKeyAccidentId(id);
    }

    @GetMapping("/register_of_accidents/$id={id}&$branch={branch}")
    public RegistryOfAccidents findAllByAccidentByIdAndBranch(@PathVariable (value = "id") Long id, @PathVariable (value="branch") Integer branch)
    {
        return register.findByKeyResponsibleBranchAndKeyAccidentId(branch, id);
    }

    @PostMapping("/register_of_accidents")
    public RegistryOfAccidents addAccident(@RequestBody RegistryOfAccidents reg) {
        return register.save(reg);
    }

}
