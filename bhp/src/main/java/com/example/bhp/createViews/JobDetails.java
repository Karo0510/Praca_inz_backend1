package com.example.bhp.createViews;

import com.example.bhp.entity.HazardFactors;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Data
public class JobDetails
{
    String jobName;
    LocalDate lastRisk;
    Integer nrOfDepartment;
    List<HazardFactors> factors;
}
