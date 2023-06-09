package com.example.bhp.controller;

import com.example.bhp.dao.JobInfo;
import com.example.bhp.dao.RiskInfo;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.repository.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

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
    public RiskInfo currentRiskByJobId(@PathVariable(value="dep") Integer dep, @PathVariable(value="id")Long id)
    {
        RiskInfo ans = RiskInfo.downloadCurrentRiskByJobIdAndDepartment(dep, id);

        System.out.println(ans);

        return ans;
    }

    @PostMapping("/risk")
    public RiskAssessment addRisk(@RequestBody RiskAssessment reg) {
        return register.save(reg);
    }

}
