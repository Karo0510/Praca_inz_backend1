package com.example.bhp.controller;

import com.example.bhp.createViews.EmployeeBasics;
import com.example.bhp.createViews.EmployeeDetails;
import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.repository.EmployeeRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//autowired - sam stworzy obiekt serwisu w tym kontrolerze


@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequestMapping("/api")
@EntityScan(basePackages = {"com.example"})
public class EmployeeController
{
    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeRepository employeeRepository, EntityManagerFactory emf) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/$id={id}")
    public ResponseEntity<String> yourEndpoint(@PathVariable(value="id") Long id) {
        // Obsługa wartości Long
        System.out.println("Przekazano wartość Long: " + id);

        // Zwróć odpowiedź
        return ResponseEntity.ok("Odpowiedź z serwera");
    }

    @GetMapping("/employees/$id={id}/detail")
    public EmployeeDetails detailsEmployee(@PathVariable(value="id")Long id)
    {
        EmployeeDetails details = new EmployeeDetails().setData(EmployeeInfo.getEmployeeById(id));

        return details;
    }

    //@GetMapping("/employees/$id={id}/detail")
    /*@GetMapping("/employees/$id={id}/detail")
    public Employees detailsEmployee(@PathVariable(value="id")Long id)
    {
        Employees e = new Employees();
        try
        {
            e = employeeRepository.getReferenceById(id);
        }catch(Exception ex)
        {
            return null;
        }

        return e;
    }*/

    /*@GetMapping("/employees/{offset}/{size}")
    public List<Employees> fetchEmployees(@PathVariable(value="offset") Integer offset, @PathVariable(value="size") Integer size)
    {
        return employeeRepository.findAll(PageRequest.of(offset, size)).toList();
    }*/

    /*@GetMapping("/employees/{field}")
    public List<Employees> fetchEmployees(@PathVariable(value="field") String field)
    {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }*/


    @GetMapping("/employees")
    public List<EmployeeBasics> fetchEmployees()
    {
        List<EmployeeBasics> emp = new ArrayList<>();

        List<EmployeeInfo> info = EmployeeInfo.getEmployeesAllData(null);

        for (EmployeeInfo i: info)
        {
            EmployeeBasics e = new EmployeeBasics();
            System.out.println(i.getEmployee().getLastName());

            e = e.setData(i);
            emp.add(e);
        }

        return emp;
    }

    @GetMapping("/employees/$department={id}")
    public List<EmployeeBasics> fetchEmployees(@PathVariable(value="id") Long number)
    {
        List<EmployeeBasics> emp = new ArrayList<>();

        List<EmployeeInfo> info = EmployeeInfo.getEmployeesAllData(number);

        for (EmployeeInfo i: info)
        {
            EmployeeBasics e = new EmployeeBasics();
            System.out.println(i.getEmployee().getLastName());

            e = e.setData(i);
            emp.add(e);
        }

        return emp;
    }

    /*@GetMapping("/employees")
    public List<Employees> fetchEmployees()
    {
        return employeeRepository.findAll();
    }*/


    @PostMapping("/employees")
    public Employees addEmployee(@RequestBody Employees employee) {
        return employeeRepository.save(employee);
    }
}

