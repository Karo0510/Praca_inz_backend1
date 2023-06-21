package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
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
public class RiskInfo
{
    public RiskAssessment ra;
    public List<HazardFactors> hf = new ArrayList<>();

    public static List<RiskAssessment> checkAllRisks()
    {
        Session session = DBConnection.getSession();

        try
        {
            List<RiskAssessment> list = session.createQuery("Select ra from RiskAssessment ra", RiskAssessment.class).getResultList();

            return list;

        }finally{
            session.close();
        }
    }

    public List<RiskInfo> downloadData()
    {
        List<RiskInfo> ans = new ArrayList<>();
        Session session = DBConnection.getSession();

        try
        {
            List<Object[]> list = session.createQuery("Select ra, hf from RiskAssessment ra left join HazardFactors on ra.factors = hf", Object[].class).list();

            for (Object[] entry: list)
            {
                ans.add(new RiskInfo((RiskAssessment) entry[0], (List<HazardFactors>) entry[1]));
            }

            return ans;

        }finally{
            session.close();
        }
    }

    public static RiskInfo downloadCurrentRiskByJobId(Long id)
    {
        Session session = DBConnection.getSession();

        try
        {
            String hql = "SELECT ra FROM RiskAssessment ra\n" +
                    "WHERE ra.jobPosition.id = :id\n" +
                    "AND ra.date = (\n" +
                    "    SELECT MAX(tab.date) FROM RiskAssessment tab WHERE tab.jobPosition.id = :id\n" +
                    ")";

            Query<RiskAssessment> ans = session.createQuery(hql, RiskAssessment.class);
            ans.setParameter("id", id);

            RiskAssessment a = ans.getSingleResult();

            return new RiskInfo(a, a.getFactors());

        }finally{
            session.close();
        }
    }

    public static RiskInfo downloadCurrentRiskByJobIdAndDepartment(Integer dep, Long id)
    {
        Session session = DBConnection.getSession();

        try
        {
            String hql = "Select ra from RiskAssessment ra\n" +
                    "where ra.jobPosition.id = :id\n" +
                    "and ra.date = (Select MAX(ra.date) from RiskAssessment ra)\n" +
                    "and ra.nr_of_department = :dep";

            Query<RiskAssessment> ans = session.createQuery(hql, RiskAssessment.class);
            ans.setParameter("dep", dep);
            ans.setParameter("id", id);

            RiskAssessment a = ans.getSingleResult();

            return new RiskInfo(a, a.getFactors());

        }finally{
            session.close();
        }
    }

    public static RiskAssessment addRisk(RiskAssessment risk)
    {
        Session session = DBConnection.getSession();

        try
        {
            session.getTransaction().begin();
            session.persist(risk);
            session.flush();
            session.getTransaction().commit();

            return risk;

        }finally{
            session.close();
        }
    }

    public static RiskAssessment addRisk(RiskAssessment risk, List<HazardFactors> hf)
    {
        if (hf != null)
        {
            for (HazardFactors h: hf)
            {
                if (!risk.getFactors().contains(h))
                    risk.getFactors().add(h);
            }
        }

        return addRisk(risk);
    }
}
