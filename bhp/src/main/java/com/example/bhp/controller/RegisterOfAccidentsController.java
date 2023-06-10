package com.example.bhp.controller;


import com.example.bhp.createViews.AccidentBasics;
import com.example.bhp.dao.AccidentInfo;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")

public class RegisterOfAccidentsController {

    @Autowired
    private RegisterOfAccidentsRepository register;


    @GetMapping("/register_of_accidents")
    public List<AccidentBasics> fetchEmployees()
    {

        List<AccidentBasics> ans = new ArrayList<>();
        List<RegistryOfAccidents> reg = register.findAll();

        for (RegistryOfAccidents r: reg)
        {
            ans.add(new AccidentBasics().setData(r));
        }

        return ans;
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

    @GetMapping("/register_of_accidents/$data={data}")
    public RegistryOfAccidents findByData(@PathVariable(value="data") String data)
    {
        String[] entry = data.split("_");
        Long l = Long.parseLong(entry[0]);
        Integer i = Integer.parseInt(entry[1]);

        //String data1 = data;
        return findAllByAccidentByIdAndBranch(l, i);
    }


    @GetMapping("/register_of_accidents/$id={id}&branch={branch}")
    public RegistryOfAccidents findAllByAccidentByIdAndBranch(@PathVariable (value = "id") Long id, @PathVariable (value="branch") Integer branch)
    {
        return register.findByKeyResponsibleBranchAndKeyAccidentId(branch, id);
    }

    @PostMapping("/register_of_accidents")
    public RegistryOfAccidents addAccident(@RequestBody RegistryOfAccidents reg) {
        return register.save(reg);
    }

}
