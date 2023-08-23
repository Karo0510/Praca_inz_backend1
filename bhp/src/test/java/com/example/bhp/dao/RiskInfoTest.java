package com.example.bhp.dao;

import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import org.hamcrest.xml.HasXPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"com.example.bhp.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RiskInfoTest
{

    @Test
    void testAddRiskWithHazardFactors()
    {
        List<JobPosition> pos = JobInfo.getAllPositions();

        HazardFactors h1 = HazardFactors.builder()
                .hazard("Oparzenie 2 stopnia")
                .causeOfHazard("Noszenie goracego kubka z herbata")
                .probability(2.0)
                .rank(2.0)
                .risk(2.0)
                .build();

        HazardFactors h2 = HazardFactors.builder()
                .hazard("Porazenie pradem")
                .causeOfHazard("Uszkodzenie przewodow")
                .probability(2.0)
                .rank(3.0)
                .risk(3.0)
                .actions("Zwracanie uwagi na stan sprzetu. Zglaszanie incydentow do przelozonego")
                .probabilityAfterPreventiveActions(1.0)
                .rankAfterPreventiveActions(3.0)
                .probabilityAfterPreventiveActions(2.0)
                .build();

        HazardFactors h3 = HazardFactors.builder()
                .hazard("Kopniecie kolegi")
                .causeOfHazard("Wkurzenie kolegi")
                .probability(1.0)
                .rank(1.0)
                .risk(1.0)
                .build();


        List<HazardFactors> hf = new ArrayList<>();
        hf.add(h1);
        hf.add(h2);
        hf.add(h3);

        RiskAssessment risk = RiskAssessment.builder()
                //.nrOfDepartment(4)
                .date(LocalDate.of(2023, 01, 01))
                .jobPosition(pos.get(2))
                .build();

        RiskAssessment ans = RiskInfo.addRisk(risk);

        assertNotNull(ans);


    }

    @Test
    void testAddRisk()
    {
        List<JobPosition> pos = JobInfo.getAllPositions();

        RiskAssessment risk = RiskAssessment.builder()
                //.nrOfDepartment(9)
                .date(LocalDate.of(2023, 01, 01))
                .jobPosition(pos.get(1))
                .build();

        RiskAssessment ans = RiskInfo.addRisk(risk);

        assertNotNull(ans);
    }
}