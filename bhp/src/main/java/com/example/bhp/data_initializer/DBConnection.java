package com.example.bhp.data_initializer;

import com.example.bhp.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBConnection {

    private static SessionFactory factory;

    static {
        try
        {
            Configuration cfg = new Configuration();
            cfg.configure("cfg.xml");

            cfg.addAnnotatedClass(Employees.class);
            cfg.addAnnotatedClass(JobPosition.class);
            cfg.addAnnotatedClass(RegistryOfAccidents.class);
            cfg.addAnnotatedClass(RiskAssessment.class);
            cfg.addAnnotatedClass(TrainingRegister.class);
            cfg.addAnnotatedClass(HazardFactors.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
                    .build();

            factory = cfg.buildSessionFactory(serviceRegistry);
        }catch(Throwable ex)
        {
            System.err.println("Inicjalizacja SessionFactory nie powiodła się: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }

    public void doWork() {}

    public static void close() {
        factory.close();
    }

}