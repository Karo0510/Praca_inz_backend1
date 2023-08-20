package com.example.bhp.createViews;

import com.example.bhp.dao.TrainingInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.TrainingRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDetails
{
    public Long id;
    public LocalDate firstTrainingDate;
    public LocalDate examTrainingDate;
    public List<Long> IdEmployees = new ArrayList<>();

    public TrainingDetails setData(TrainingRegister reg)
    {
        this.id = reg.getTraining_id();
        this.firstTrainingDate = reg.getFirst_date();
        this.examTrainingDate = reg.getDate_exam();

        for (Employees e: reg.getEmployees())
        {
            Long data = e.getId();
            IdEmployees.add(data);
        }

        return this;
    }


}


