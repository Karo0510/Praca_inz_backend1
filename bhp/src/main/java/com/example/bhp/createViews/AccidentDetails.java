package com.example.bhp.createViews;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.RegistryOfAccidents;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor


public class AccidentDetails extends AccidentBasics
{
    public List<Long> IdEmployees = new ArrayList<>();

    public AccidentDetails() {

    }

    public AccidentDetails setData(RegistryOfAccidents info)
    {
        super.setData(info);

        for (Employees emp: info.getEmployees())
        {
            this.IdEmployees.add(emp.getId());
        }

        return this;
    }
}
