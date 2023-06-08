package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class JobInfo
{
    public JobPosition jobPosition;
    public List<RiskAssessment> risk;

    public static List<JobInfo> listRisk()
    {
        List<JobInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        String hql = "SELECT j FROM JobPosition j";

        try
        {
            List<JobPosition> list = session.createQuery(hql, JobPosition.class).list();

            for (JobPosition entry : list) {
                JobPosition j = entry;
                List<RiskAssessment> r = entry.getRiskAssessments();

                ans.add(new JobInfo(j, r));
            }

            return ans;

        }finally{
            session.close();
        }

    }

    /*private static JobInfo currentRisk(Long jobId)
    {

    }*/

}
