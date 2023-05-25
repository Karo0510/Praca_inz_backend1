package com.example.bhp.createViews;

import com.example.bhp.entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo
{
    Long id;
    String lastName;
    String firstName;
    Integer NrOfTheDepartment;
    String email;
    String jobName;

    public EmployeeInfo setData(Employees e, String name)
    {
        this.id = e.getId();
        this.lastName = e.getLastName();
        this.firstName = e.getFirstName();
        this.NrOfTheDepartment = e.getNrOfDepartment();
        this.email = e.getEmail();
        this.jobName = name;

        return this;
    }
}




