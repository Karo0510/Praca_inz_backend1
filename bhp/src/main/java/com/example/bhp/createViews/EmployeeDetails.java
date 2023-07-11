package com.example.bhp.createViews;

import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.entity.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployeeDetails extends EmployeeBasics implements Serializable
{
    public List<String> numberOfProtocole = new ArrayList<>();
    public List<LocalDate> firstDayPeriodicDate = new ArrayList<>();

    public EmployeeDetails()
    {
    }

    public EmployeeDetails(Long id, String firstName, String lastName, String email, String jobPosition, Integer nrOfDepartment, List<String> numberOfProtocole, List<LocalDate> firstDayPeriodicDate)
    {
        super(id, firstName, lastName, email, jobPosition, nrOfDepartment);
        this.numberOfProtocole = numberOfProtocole;
        this.firstDayPeriodicDate = firstDayPeriodicDate;
    }

    public EmployeeDetails setData(EmployeeInfo emp)
    {
        super.setData(emp);

        for (RegistryOfAccidents ra: emp.getAccidents())
        {
            System.out.println();
            this.numberOfProtocole.add(ra.getKey().toString());
        }

        for (TrainingRegister tr: emp.getTrainings())
        {
            this.firstDayPeriodicDate.add(tr.getFirst_date());
        }

        return this;
    }

}
