package com.example.bhp.controller;

import com.example.bhp.createViews.JobDetails;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.dao.RiskInfo;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.repository.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RiskController {

    @Autowired
    private RiskAssessmentRepository register;

    @GetMapping("/risk")
    public List<JobInfo> fetchRisk()
    {
        List<JobInfo> ans = JobInfo.listRisk();

        System.out.println(ans.isEmpty());

        return ans;
    }

    @GetMapping("/risk/$department={dep}/$job_id={id}")
    public RiskInfo currentRiskByJobIdAndDepartment(@PathVariable(value="dep") Integer dep, @PathVariable(value="id")Long id)
    {
        RiskInfo ans = RiskInfo.downloadCurrentRiskByJobIdAndDepartment(dep, id);

        System.out.println(ans);

        return ans;
    }

    @GetMapping("/risk/$job_id={id}")
    public RiskInfo currentRiskByJobId(@PathVariable(value="id")Long id)
    {
        RiskInfo ans = RiskInfo.downloadCurrentRiskByJobId(id);

        System.out.println(ans);

        return ans;
    }

    @PostMapping("/risk")
    public ResponseEntity addRisk(@RequestBody JobDetails job)
    {
        if (job.getFactors().isEmpty())
        {
            return ResponseEntity.ok("Lista jest pusta");
        }

        System.out.println(job.getLastRisk());

        JobPosition foundJob = JobInfo.getJobByName(job.getJobName());

        System.out.println(job.getFactors());

        RiskAssessment riskAssessment = RiskAssessment.builder()
                .jobPosition(foundJob)
                .nrOfDepartment(job.getNrOfDepartment())
                .date(job.getLastRisk())
                .factors(job.getFactors())
                .build();

        RiskAssessment savedRisk = RiskInfo.addRisk(riskAssessment);

        if (savedRisk == null)
        {
            return ResponseEntity.ok("Nie zapisano oceny ryzyka");
        }

        return ResponseEntity.ok("ok");
    }

}
