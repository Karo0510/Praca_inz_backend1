package com.example.bhp.dao;

import com.example.bhp.createViews.TrainingDetails;
import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.RiskAssessment;
import com.example.bhp.entity.TrainingRegister;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.core.parameters.P;

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

        try {
            session.getTransaction().begin();
            session.persist(training);
            session.flush();
            session.getTransaction().commit();

            return training;
        }
        catch(Exception ex)
        {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println(ex.getMessage());
        }finally{
            session.close();
        }

        return null;
    }

    public static TrainingRegister addTraining(TrainingRegister training, Employees e)
    {
        if (!training.getEmployees().contains(e))
        {
            training.getEmployees().add(e);
            e.getPeriodic_training_register().add(training);
            Employees saved = EmployeeInfo.updateEmployee(e);
        }

        return addTraining(training);
    }

    public static TrainingRegister addTraining(TrainingRegister training, List<Employees> e)
    {
        TrainingRegister saved = null;

        try
        {
            for (Employees emp: e)
            {
                training.getEmployees().add(emp);
                emp.getPeriodic_training_register().add(training);
                saved = addTraining(training);
                Employees s = EmployeeInfo.updateEmployee(emp);
            }

            System.out.println(saved);
        }catch(Exception ex)
        {
            System.out.println("PROBLEM");
            System.exit(-1000);
        }

        return saved;
    }


    public static TrainingRegister addTraining(TrainingDetails training, List<Employees> e)
    {
        TrainingRegister saved = null;

        TrainingRegister ans = TrainingRegister.builder()
                .first_date(training.getFirstTrainingDate())
                .date_exam(training.examTrainingDate)
                .build();

        saved = addTraining(ans);

        try
        {
            for (Employees emp: e)
            {
                ans.getEmployees().add(emp);
                emp.getPeriodic_training_register().add(ans);
                Employees s = EmployeeInfo.updateEmployee(emp);
            }

            System.out.println(saved);
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return saved;
    }


}
