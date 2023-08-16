package com.example.bhp.controller;

import com.example.bhp.createViews.JobDetails;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.dao.RiskInfo;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.repository.RiskAssessmentRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        //XXX: poprawić walidacje funkcji findRisk!!!!
        System.out.println(job.getJobName());

        if (job.getFactors().isEmpty())
            return new ResponseEntity<String>("Lista czynnikow jest pusta", HttpStatus.NOT_ACCEPTABLE);

        System.out.println(job.getLastRisk());

        JobPosition foundJob = JobInfo.getJobByName(job.getJobName());

        System.out.println(foundJob.getName());

        RiskAssessment riskAssessment = RiskAssessment.builder()
                .jobPosition(foundJob)
                .nrOfDepartment(job.getNrOfDepartment())
                .date(job.getLastRisk())
                .factors(job.getFactors())
                .build();

        for (int i = 0; i<job.getFactors().size(); i++)
            riskAssessment.getFactors().get(i).setRiskAssessment(riskAssessment);

        RiskAssessment savedRisk = null;

        try
        {
            savedRisk = RiskInfo.addRisk(riskAssessment);
        }catch(NonUniqueResultException ex)
        {
            return new ResponseEntity<String>("Już stworzono ocene ryzyka w dniu "+ job.getLastRisk() + " dla stanowiska "+job.getJobName(), HttpStatus.NOT_ACCEPTABLE);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<String>("Problem z zapisem", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("ok");
    }

}
