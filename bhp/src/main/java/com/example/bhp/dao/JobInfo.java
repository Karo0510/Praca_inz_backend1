package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class JobInfo
{
    public JobPosition jobPosition;
    public List<RiskAssessment> risk;

    public static List<JobPosition> getAllPositions()
    {
        Session session = DBConnection.getSession();

        String hql = "SELECT j FROM JobPosition j";

        try
        {
            TypedQuery<JobPosition> query = session.createQuery(hql, JobPosition.class);

            List<JobPosition> job = query.getResultList();

            return job;

        }finally{
            session.close();
        }

    }

    public static JobPosition getById(Long id)
    {
        Session session = DBConnection.getSession();

        String hql = "SELECT j FROM JobPosition j where id=:id";

        try
        {
            TypedQuery<JobPosition> query = session.createQuery(hql, JobPosition.class);
            query.setParameter("id", id);

            JobPosition job = query.getSingleResult();

            System.out.println(job.getName());

            return job;

        }finally{
            session.close();
        }

    }

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

    public static JobPosition getJobByName(String jobName)
    {
        Session session = DBConnection.getSession();

        String hql = "SELECT j FROM JobPosition j where j.name=:name";

        try
        {
            TypedQuery<JobPosition> query = session.createQuery(hql, JobPosition.class);
            query.setParameter("name", jobName);

            JobPosition job = query.getSingleResult();

            System.out.println(job.getName());

            return job;
        }
        catch(NoResultException ex)
        {
            System.out.println("Brak rezultatu");
            return null;
        }
        finally{
            session.close();
        }
    }

    public static boolean validation(JobPosition job)
    {
        List<JobPosition> positions = JobInfo.getAllPositions();

        if (positions.contains(job))
        {
            return true;
        }

        return false;
    }

    public static JobPosition addJobPosition(JobPosition job)
    {

        if (JobInfo.validation(job) == false)
        {
            Session session = DBConnection.getSession();

            try
            {
                session.getTransaction().begin();
                session.persist(job);
                session.flush();
                session.getTransaction().commit();

                return job;

            }finally{
                session.close();
            }
        }
        else
        {
            return null;
        }
    }


    public JobPosition addJobPosition(JobPosition job, RiskAssessment risk, Employees employee)
    {
       if (risk != null)
           job.getRiskAssessments().add(risk);

       if (employee != null)
           job.getEmployees().add(employee);

       return addJobPosition(job);
    }


}
