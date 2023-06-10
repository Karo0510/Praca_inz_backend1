package com.example.bhp.createViews;

import com.example.bhp.dao.AccidentInfo;
import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.entity.RegistryOfAccidents;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccidentBasics
{
    public String key;
    public String protocole;
    public String place;
    public LocalDate date;
    public Integer numberOfVictims;
    public RegistryOfAccidents.Accident_priority accident_priority;
    public boolean accident;

    public AccidentBasics setData(RegistryOfAccidents info)
    {
        this.key = info.getKey().toString();
        this.protocole = info.getProtocole();
        this.place = info.getPlace();
        this.date = info.getDate();
        this.numberOfVictims = info.getNumber();
        this.accident_priority = info.getType();
        this.accident = info.isAccident();

        return this;
    }

}
