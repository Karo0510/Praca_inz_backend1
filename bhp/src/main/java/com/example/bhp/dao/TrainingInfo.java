package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.entity.TrainingRegister;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TrainingInfo
{

    public static List<TrainingRegister> trainings()
    {
        List<TrainingRegister> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        try
        {
            Query<TrainingRegister> list = session.createNamedQuery("trainings", TrainingRegister.class);
            return list.getResultList();

        }finally{
            session.close();
        }
    }

    public static TrainingRegister addTraining(TrainingRegister training)
    {
        Session session = DBConnection.getSession();

        try
        {
            session.getTransaction().begin();
            session.persist(training);
            session.flush();
            session.getTransaction().commit();

            return training;

        }finally{
            session.close();
        }
    }


}
