package com.example.bhp.controller;

import com.example.bhp.createViews.EmployeeDetails;
import com.example.bhp.createViews.EmployeeInfo;
import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.repository.EmployeeRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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

    //@GetMapping("/employees/$id={id}/detail")
    @GetMapping("/employees/$id={id}/detail")
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
    public List<EmployeeInfo> fetchEmployees()
    {
        List<EmployeeInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();
        //SELECT e, j.job_position_name FROM Employees e LEFT JOIN e.jobPosition j WHERE e.nrOfDepartment = 9"

        try
        {
            Query<Object[]> list = session.createQuery(
                    "SELECT e, j.name FROM Employees e, JobPosition j WHERE e.jobPosition = j", Object[].class);

            for (Object[] entry: list.list()) {
                ans.add(new EmployeeInfo().setData((Employees) entry[0], (String)entry[1]));
            }

            return ans;

        }finally{
            session.close();
        }
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

