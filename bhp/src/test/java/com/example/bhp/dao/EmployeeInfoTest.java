package com.example.bhp.dao;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"com.example.bhp.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeInfoTest {

    @Test
    @Rollback
    void addEmployeeTest()
    {
        JobPosition position = JobPosition.builder()
                .name("Lekarz")
                .isMentalStress(false)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(true)
                .build();


        JobPosition pos = JobInfo.addJobPosition(position);

        assertNotNull(pos);

        Employees employee1 = Employees.builder()
                .firstName("Karolina")
                .lastName("Maciejewska")
                .email("karma0510@gmail.com")
                .date(LocalDate.of(2022, 1, 1))
                .lastTrainingDate(LocalDate.of(2022, 1, 2))
                .nrOfDepartment(9)
                .jobPosition(position)
                .build();

        Employees emp = EmployeeInfo.AddEmployee(employee1);

        assertNotNull(emp);

        Employees employee2 = Employees.builder()
                .firstName("Karolina")
                .lastName("Maciejewska")
                .email("karma0510@gmail.com")
                .date(LocalDate.of(2022, 1, 1))
                .lastTrainingDate(LocalDate.of(2022, 1, 2))
                .nrOfDepartment(9)
                .jobPosition(position)
                .build();

        Employees emp2 = EmployeeInfo.AddEmployee(employee2);

        assertNull(emp2);

    }

}