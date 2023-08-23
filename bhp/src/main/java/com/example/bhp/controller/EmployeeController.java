package com.example.bhp.controller;

import com.example.bhp.createViews.EmployeeBasics;
import com.example.bhp.createViews.EmployeeDetails;
import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<EmployeeBasics>fetchEmployees()
    {
        List<EmployeeBasics> emp = new ArrayList<>();

        List<EmployeeInfo> info = EmployeeInfo.getEmployeesAllData(null);

        for (EmployeeInfo i: info)
        {
            EmployeeBasics e = new EmployeeBasics();
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


    /*@PostMapping("/employees")
    public Employees addEmployee(@RequestBody Employees employee)
    {
        return employeeRepository.save(employee);
    }*/

    /*@PostMapping("/employees")
    public ResponseEntity add(@RequestBody EmployeeDetails employee) {

        Employees emp = Employees.builder()
                .firstName(employee.firstName)
                .lastName(employee.lastName)
                .nrOfDepartment(employee.nrOfDepartment)
                .email(employee.email)
                .date(LocalDate.now())
                .build();

        JobPosition job = JobInfo.getJobByName(employee.jobPosition);

        if (job == null)
        {
            return ResponseEntity.ok("Nie znaleziono stanowiska. Proszę, podaj inne lub uzupełnij je w sekcji Job");
        }
        else
        {
            emp.setJobPosition(job);
        }

        Employees savedEmp = EmployeeInfo.addEmployee(emp);

        if (savedEmp == null)
        {
            return ResponseEntity.ok("Nie dodano pracownika");
        }

        return ResponseEntity.ok("Pracownik zostal zapisany");
    }*/

    @PostMapping("/add_employees")
    public ResponseEntity<String> addEmployees(@RequestBody List<EmployeeBasics> employee)
    {
        int count = 0;

        String text = "";

        if (employee.isEmpty())
        {
            return new ResponseEntity<String>("Lista jest pusta", HttpStatus.NOT_ACCEPTABLE);
        }


        for (int i = 0; i < employee.size(); i++)
        {
            Integer l = i + 1;

            JobPosition job = JobInfo.getJobByName(employee.get(i).getJobPosition());

            if (job == null)
            {
                text += l.toString() + ") Nie ma takiego stanowiska pracy - najpierw dodaj stanowisko pracy "+employee.get(i).getJobPosition()+"\n";
                continue;
            }

            Employees savedEmp = EmployeeBasics.getEmployee(employee.get(i));

            if (savedEmp != null)
                text += l.toString() + ") Zapisano uzytkownika  " + employee.get(i).lastName+"\n";
            else
            {
                text += l.toString() + ") Nie mozna zapisac pracownika  " + employee.get(i).lastName+"\n";
            }

        }
        return ResponseEntity.ok(text);
    }

}

