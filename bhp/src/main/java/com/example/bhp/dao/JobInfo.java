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

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class JobInfo
{
    public JobPosition jobPosition;
    public RiskAssessment risk;
    public HazardFactors hf;

    public static List<JobInfo> listRisk()
    {
        List<JobInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        try
        {
            List<Object[]> list = session.createQuery("Select j, ra, f from JobPosition j left join RiskAssessment ra on ra.jobPosition = j left join ra.factors f on f.riskAssessment = ra", Object[].class).getResultList();

            for (Object[] entry: list)
            {
                if (entry[1] != null)
                {
                    ans.add(new JobInfo((JobPosition) entry[0], (RiskAssessment) entry[1], (HazardFactors) entry[2]));
                }
                else
                {
                    ans.add(new JobInfo((JobPosition) entry[0], null, null));
                }



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
