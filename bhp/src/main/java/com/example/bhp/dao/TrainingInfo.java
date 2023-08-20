package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
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

    public static TrainingRegister training(Long id)
    {
        Session session = DBConnection.getSession();

        try
        {
            Query<TrainingRegister> ans = session.createQuery("Select t from TrainingRegister t where t.training_id = :id", TrainingRegister.class);
            ans.setParameter("id", id);

            return ans.getSingleResult();

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

    public static TrainingRegister addTraining(TrainingRegister training, Employees e)
    {
        if (!training.getEmployees().contains(e))
        {
            training.getEmployees().add(e);
        }

        return addTraining(training);
    }

    public static TrainingRegister addTraining(TrainingRegister training, List<Employees> e)
    {
        for (Employees emp: e)
        {
            if (!training.getEmployees().contains(emp))
                training.getEmployees().add(emp);
        }
        
        return addTraining(training);
    }


}
