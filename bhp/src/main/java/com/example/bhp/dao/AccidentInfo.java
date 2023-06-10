package com.example.bhp.dao;

import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.repository.RegisterOfAccidentsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class AccidentInfo
{
    public RegistryOfAccidents reg;

    @Autowired
    private RegisterOfAccidentsRepository register;

    public AccidentInfo(RegisterOfAccidentsRepository register)
    {
        this.register = register;
    }


    public List<RegistryOfAccidents> accidentListByResponsibleBranch(Integer branch)
    {
        return register.findByKeyResponsibleBranch(branch);
    }

    public List<RegistryOfAccidents> accidentListById (Long id)
    {
        return register.findByKeyAccidentId(id);
    }

    public RegistryOfAccidents accidentByBranchAndId(Integer branch, Long id)
    {
        return register.findByKeyResponsibleBranchAndKeyAccidentId(branch, id);
    }

    public List<RegistryOfAccidents> accidents()
    {
        return register.findAll();
    }



}
