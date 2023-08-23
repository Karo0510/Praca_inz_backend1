package com.example.bhp.controller;

import com.example.bhp.createViews.JobDetails;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiskControllerTest {


    @Test
    void fetchRisk() {
    }

    @Test
    void currentRiskByJobIdAndDepartment() {
    }

    @Test
    void currentRiskByJobId() {
    }

    @Test
    void addRiskTest()
    {
        /*JobPosition newJob = JobPosition.builder()
                .name("Kurier")
                .isBiologicalRisk(true)
                .isChemicalRisk(true)
                .isMentalStress(true)
                .isPhysicalRisk(true)
                .build();


        JobPosition pos = JobInfo.addJobPosition(newJob);*/

        HazardFactors h5 = HazardFactors.builder()
                .hazard("proba 1")
                .causeOfHazard("proba 1")
                .probability(2.0)
                .rank(3.0)
                .risk(3.0)
                .actions("proba 1")
                .probabilityAfterPreventiveActions(1.0)
                .rankAfterPreventiveActions(3.0)
                .probabilityAfterPreventiveActions(2.0)
                .build();

        HazardFactors h6 = HazardFactors.builder()
                .hazard("proba 1")
                .causeOfHazard("proba 1")
                .probability(2.0)
                .rank(3.0)
                .risk(3.0)
                .actions("proba 1")
                .probabilityAfterPreventiveActions(1.0)
                .rankAfterPreventiveActions(3.0)
                .probabilityAfterPreventiveActions(2.0)
                .build();

        List<HazardFactors> list = new ArrayList<>();
        list.add(h5);
        list.add(h6);

        JobDetails job = new JobDetails();

        job.setJobName("Kurier");
        job.setLastRisk(LocalDate.of(2035, 9, 9));
        //job.setNrOfDepartment(9);
        job.setFactors(list);

        RiskController risk = new RiskController();

        ResponseEntity<String> ans = risk.addRisk(job);

        System.out.println(ans.getBody());

        assertTrue(ans.equals("ok"));



    }
}