package com.example.bhp.dao;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.entity.TrainingRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo
{
    Employees employee;
    JobPosition jobPosition;
    List<RegistryOfAccidents> accidents;
    List<TrainingRegister> trainings;


    public static List<Object[]> getEmployees()
    {
        List<Object[]> ans = new ArrayList<>();

        Session session = DBConnection.getSession();
        //SELECT e, j.job_position_name FROM Employees e LEFT JOIN e.jobPosition j WHERE e.nrOfDepartment = 9"

        try
        {
            //List<Object[]> list = session.createNamedQuery("get_all_employees", Object[].class).list();
            List<Object[]> list = session.createQuery("SELECT e, j FROM Employees e LEFT JOIN JobPosition j ON e.jobPosition = j", Object[].class).getResultList();

            return list;

        }finally{
            session.close();
        }
    }

    private static List<Object[]> getEmployees(Long number)
    {
        List<EmployeeInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        try
        {

            Query<Object[]> list = session.createNamedQuery("get_all_employees_by_department", Object[].class);
            list.setParameter("id", number);

            return list.list();

        }finally{
            session.close();
        }
    }

    private static EmployeeInfo createEmployeeInfo(Object[] entry)
    {
        return new EmployeeInfo((Employees) entry[0], (JobPosition) entry[1],((Employees) entry[0]).getRegister_of_accidents(), ((Employees) entry[0]).getPeriodic_training_register());
    }

    public static List<EmployeeInfo> getEmployeesAllData(Long number)
    {
        List<EmployeeInfo> res = new ArrayList<>();

        try
        {

            List<Object[]> ans = new ArrayList<>();

            if (number == null)
            {
                ans = EmployeeInfo.getEmployees();
            }
            else
            {
                ans = EmployeeInfo.getEmployees(number);
            }


            for (Object[] entry: ans) {
                res.add(EmployeeInfo.createEmployeeInfo(entry));
            }

            return res;

        }catch(Exception ex)
        {
            return null;
        }
    }


    public static EmployeeInfo getEmployeeById(Long number)
    {
        List<EmployeeInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        try
        {
            Query<Object[]> list = session.createNamedQuery("get_employee_by_id", Object[].class);
            list.setParameter("id", number);

            return EmployeeInfo.createEmployeeInfo(list.getSingleResult());

        }finally{
            session.close();
        }
    }
}




