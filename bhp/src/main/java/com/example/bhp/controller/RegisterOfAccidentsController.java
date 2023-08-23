package com.example.bhp.controller;


import com.example.bhp.converter.StringToPriorityConverter;
import com.example.bhp.createViews.AccidentBasics;
import com.example.bhp.createViews.AccidentDetails;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegisterOfAccidentsController {

    @Autowired
    private RegisterOfAccidentsRepository register;

    @Autowired
    private StringToPriorityConverter stringToYourClassConverter;

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
    public AccidentDetails findByData(@PathVariable(value="data") String data)
    {
        String[] entry = data.split("_");
        Long l = Long.parseLong(entry[0]);
        Integer i = Integer.parseInt(entry[1]);

        System.out.println(findAllByAccidentByIdAndBranch(l, i));
        //String data1 = data;
        return findAllByAccidentByIdAndBranch(l, i);
    }


    @GetMapping("/register_of_accidents/$id={id}&branch={branch}")
    public AccidentDetails findAllByAccidentByIdAndBranch(@PathVariable (value = "id") Long id, @PathVariable (value="branch") Integer branch)
    {
        RegistryOfAccidents reg =  register.findByKeyResponsibleBranchAndKeyAccidentId(branch, id);
        AccidentDetails ans = new AccidentDetails().setData(reg);

        return ans;
    }

    //@PostMapping("/register_of_accidents")
    //public RegistryOfAccidents addAccident(@RequestBody RegistryOfAccidents reg) {
        //return register.save(reg);
   // }


    /*@PostMapping("/register_of_accidents")
    public ResponseEntity<String> addAccident(@RequestBody ArrayList<AccidentDetails> accident)
    {
        if (accident.size() == 0)
        {
            return new ResponseEntity<String>("Przeslana tabela jest pusta", HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Etap 1\n");
        for (int i = 0; i< accident.size(); i++)
        {
            System.out.println("Etap 2\n");
            AccidentDetails acc = (AccidentDetails) accident.get(i);
            System.out.println(acc.getAccident_priority());
            System.out.println("Etap 3\n");
        }

        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }*/


    @PostMapping("/register_of_accidents")
    public ResponseEntity<String> addAccident(@RequestBody ArrayList<AccidentDetails> accident) {
        System.out.println("nodkfckvd");
        String text = "";
        int count = 0;

        System.out.println("SSSSSSS: "+accident.size());

        if (accident.size() == 0)
        {
            return new ResponseEntity<String>("Przeslana tabela jest pusta", HttpStatus.NOT_ACCEPTABLE);
        }

        for (Integer i = 0; i<accident.size(); i++)
        {
            if (register.findByProtocoleAndKeyResponsibleBranch(accident.get(i).getProtocole(), accident.get(i).getDepartment()) != null)
            {
                text += "Kontrola nr "+accident.get(i).getProtocole()+"w dziale "+accident.get(i).getDepartment()+" jest juz w bazie\n";
                continue;
            }

            RegistryOfAccidents acc = accident.get(i).createAccident();

            try
            {
                RegistryOfAccidents saved = register.saveAndFlush(acc);

            }catch(Exception ex)
            {
                text += "Kontrola nr "+accident.get(i).getProtocole()+" w dziale "+accident.get(i).getDepartment()+" nie moze zostac zapisana\n";
            }

            text += "Kontrola nr "+accident.get(i).getProtocole()+"w dziale "+accident.get(i).getDepartment()+" zostala zapisana\n";

        }
        return new ResponseEntity<String>(text, HttpStatus.CREATED);
    }
}
