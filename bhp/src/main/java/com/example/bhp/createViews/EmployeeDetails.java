package com.example.bhp.createViews;

import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.entity.RegistryKey;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.entity.TrainingRegister;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails extends EmployeeBasics implements Serializable
{
    public List<RegistryKey> numberOfProtocole = new ArrayList<>();
    public List<LocalDate> firstDayPeriodicDate = new ArrayList<>();

    public EmployeeDetails()
    {
    }

    public EmployeeDetails(String firstName, String lastName, String email, String jobPosition, Integer nrOfDepartment, List<RegistryKey> numberOfProtocole, List<LocalDate> firstDayPeriodicDate)
    {
        super(firstName, lastName, email, jobPosition, nrOfDepartment);
        this.numberOfProtocole = numberOfProtocole;
        this.firstDayPeriodicDate = firstDayPeriodicDate;
    }

    public EmployeeDetails setData(EmployeeInfo emp)
    {
        super.setData(emp);

        for (RegistryOfAccidents ra: emp.getAccidents())
        {
            System.out.println();
            this.numberOfProtocole.add(ra.getKey());
        }

        for (TrainingRegister tr: emp.getTrainings())
        {
            this.firstDayPeriodicDate.add(tr.getFirst_date());
        }

        return this;
    }
}
