package com.example.bhp.createViews;

import com.example.bhp.data_initializer.DBConnection;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.GetMapping;

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

    /*public EmployeeInfo setData(Employees e, String name)
    {
        this.id = e.getId();
        this.lastName = e.getLastName();
        this.firstName = e.getFirstName();
        this.NrOfTheDepartment = e.getNrOfDepartment();
        this.email = e.getEmail();
        this.jobName = name;

        return this;
    }*/

    public static List<EmployeeInfo> getEmployees()
    {
        List<EmployeeInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();
        //SELECT e, j.job_position_name FROM Employees e LEFT JOIN e.jobPosition j WHERE e.nrOfDepartment = 9"

        try
        {

            Query<Object[]> list = session.createNamedQuery(
                    "get_all_employees", Object[].class);

            for (Object[] entry: list.list()) {
                ans.add(new EmployeeInfo((Employees) entry[0], (JobPosition) entry[1]));
            }

            return ans;

        }finally{
            session.close();
        }
    }

    public static List<EmployeeInfo> getEmployees(Long number)
    {
        List<EmployeeInfo> ans = new ArrayList<>();

        Session session = DBConnection.getSession();

        try
        {

            Query<Object[]> list = session.createNamedQuery(
                    "get_all_employees_by_department", Object[].class);
            list.setParameter("id", number);

            for (Object[] entry: list.list()) {
                ans.add(new EmployeeInfo((Employees) entry[0], (JobPosition) entry[1]));
            }

            return ans;

        }finally{
            session.close();
        }
    }
}




