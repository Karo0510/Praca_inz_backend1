package com.example.bhp.createViews;

import com.example.bhp.entity.Employees;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrainingDetails
{
    public LocalDate firstTrainingDate;
    public LocalDate examTrainingDate;
    List<String> employees = new ArrayList<>();




}


