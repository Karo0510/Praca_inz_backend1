package com.example.bhp.createViews;

import com.example.bhp.dao.EmployeeInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
}
