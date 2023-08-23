package com.example.bhp.createViews;

import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBasics
{
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String jobPosition;
    public Integer nrOfDepartment;

    public EmployeeBasics setData(EmployeeInfo emp)
    {
        System.out.println(emp.getJobPosition().getName());
        this.id = emp.getEmployee().getId();
        this.firstName = emp.getEmployee().getFirstName();
        this.lastName = emp.getEmployee().getLastName();
        this.email = emp.getEmployee().getEmail();
        this.jobPosition = emp.getJobPosition().getName();
        this.nrOfDepartment = emp.getEmployee().getNrOfDepartment();

        return this;
    }

    public static Employees getEmployee(EmployeeBasics emp)
    {
        Employees employee = Employees.builder()
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .nrOfDepartment(emp.getNrOfDepartment())
                .email(emp.getEmail())
                .date(LocalDate.now())
                .build();

        JobPosition job = JobInfo.getJobByName(emp.getJobPosition());

        if (job == null)
        {
            return null;
        }

        employee.setJobPosition(job);


        Employees savedEmp = EmployeeInfo.addEmployee(employee);

        return savedEmp;
    }
}
