package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.HazardFactors;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RiskAssessment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class RiskInfo
{
    RiskAssessment ra;
    List<HazardFactors> hf = new ArrayList<>();

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

}
