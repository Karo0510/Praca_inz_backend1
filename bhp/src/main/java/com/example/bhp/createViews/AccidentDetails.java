package com.example.bhp.createViews;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.RegistryOfAccidents;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class EmployeeBasic
{
    public Long id;
    public String firstName;
    public String LastName;
}

public class AccidentDetails extends AccidentBasics
{
    public List<EmployeeBasic> data = new ArrayList<>();

    public AccidentDetails setData(RegistryOfAccidents info)
    {
        super.setData(info);

        for (Employees emp: info.getEmployees())
        {
            this.data.add(new EmployeeBasic(emp.getId(), emp.getFirstName(), emp.getLastName()));
        }

        return this;
    }
}
