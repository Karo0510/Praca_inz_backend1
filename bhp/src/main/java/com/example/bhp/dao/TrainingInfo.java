package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.TrainingRegister;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TrainingInfo
{


    private static List<TrainingRegister> trainings()
    {
        List<TrainingRegister> ans = new ArrayList<>();

        Session session = DBConnection.getSession();
        //SELECT e, j.job_position_name FROM Employees e LEFT JOIN e.jobPosition j WHERE e.nrOfDepartment = 9"

        try
        {

            Query<TrainingRegister> list = session.createNamedQuery("trainings", TrainingRegister.class);
            return list.getResultList();

        }finally{
            session.close();
        }
    }


    private static List<Object[]> trainingsDetails()
    {

        //XXX: implementacja pobierajaca daty treningu wraz z ich uczestnikami
        List<TrainingRegister> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        //SELECT e, j.job_position_name FROM Employees e LEFT JOIN e.jobPosition j WHERE e.nrOfDepartment = 9"

        try
        {
            TypedQuery<Object[]> list = em.createNamedQuery("trainings", Object[].class);

            return list.getResultList();

        }finally{
            session.close();
        }
    }


}
