package com.example.bhp.createViews;

import com.example.bhp.converter.StringToBooleanConverter;
import com.example.bhp.converter.StringToPriorityConverter;
import com.example.bhp.dao.AccidentInfo;
import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.RegistryKey;
import com.example.bhp.entity.RegistryOfAccidents;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccidentBasics
{
    public String key;
    public String protocole;
    public Integer department;
    public String place;
    public LocalDate date;
    public Integer numberOfVictims;
    public RegistryOfAccidents.Accident_priority accident_priority;
    public boolean accident;

    public AccidentBasics setData(RegistryOfAccidents info)
    {
        this.key = info.getKey().toString();
        this.protocole = info.getProtocole();
        this.department = info.getKey().getResponsibleBranch();
        this.place = info.getPlace();
        this.date = info.getDate();
        this.numberOfVictims = info.getNumber();
        this.accident_priority = info.getType();
        this.accident = info.isAccident();

        return this;
    }


}
