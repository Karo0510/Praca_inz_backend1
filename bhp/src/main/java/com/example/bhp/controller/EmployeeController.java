package com.example.bhp.controller;

import com.example.bhp.entity.Employees;
import com.example.bhp.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//autowired - sam stworzy obiekt serwisu w tym kontrolerze


@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequestMapping("/api")
public class EmployeeController
{

    @Autowired
    private EmployeeRepository employeeRepository;
    private final EntityManagerFactory emf;

    private Integer page;
    public Long responseId;

    public EmployeeController(EmployeeRepository employeeRepository, EntityManagerFactory emf) {
        this.employeeRepository = employeeRepository;
        this.emf = emf;
    }

    @GetMapping("/employees/$id={id}")
    public ResponseEntity<String> yourEndpoint(@PathVariable(value="id") Long id) {
        // Obsługa wartości Long
        System.out.println("Przekazano wartość Long: " + id);
        responseId = id;

        // Zwróć odpowiedź
        return ResponseEntity.ok("Odpowiedź z serwera");
    }

    //@GetMapping("/employees/$id={id}/detail")
    @GetMapping("/employees/$id={id}/detail")
    public Employees detailsEmployee(@PathVariable(value="id")Long id)
    {
        Employees e = employeeRepository.getReferenceById(id);
        System.out.println(e.getLastName());
        return e;
    }

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
    public List<Employees> fetchEmployees()
    {
        return employeeRepository.findAll();
    }


    @PostMapping("/employees")
    public Employees addEmployee(@RequestBody Employees employee) {
        return employeeRepository.save(employee);
    }
}

