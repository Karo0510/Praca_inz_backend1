package com.example.bhp.dao;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"com.example.bhp.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobInfoTest {

    @Test
    void getByIdTest()
    {
        JobPosition job = JobInfo.getById(1L);

        assertNotNull(job);
    }

    @Test
    void getByIdTestIfResultNotExistsException()
    {
        assertThrows(NoResultException.class, () -> {
            JobPosition job = JobInfo.getById(10L);
        });
    }

    @Test
    void addJobWithEmployee()
    {
        JobPosition position = JobPosition.builder()
                .name("Inspektor")
                .isMentalStress(true)
                .isBiologicalRisk(true)
                .isPhysicalRisk(false)
                .isChemicalRisk(false)
                .build();


        Employees employee = Employees.builder()
                .firstName("ABCD")
                .lastName("EFGH")
                .email("karma0510@gmail.com")
                .date(LocalDate.of(2022, 1, 1))
                .lastTrainingDate(LocalDate.of(2022, 1, 2))
                .nrOfDepartment(9)
                .jobPosition(position)
                .build();

        position.getEmployees().add(employee);

        JobPosition job = JobInfo.addJobPosition(position);
        assertNotNull(job);

        EmployeeInfo e = EmployeeInfo.getEmployeeById(employee.getId());

        assertNotNull(e);


    }

    @Test
    void listRisk() {
    }

    @Test
    void findJobPosition()
    {
        JobPosition position = JobPosition.builder()
                .name("Lekarz")
                .isMentalStress(false)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(true)
                .build();

        JobPosition job = JobInfo.getById(position.getId());

        assertNotNull(job);
    }

    @Test
    void testAddJobPosition() {

        JobPosition position = JobPosition.builder()
                .name("Lekarz")
                .isMentalStress(false)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(true)
                .build();


        JobPosition job = JobInfo.addJobPosition(position);

        assertNull(job);

        JobPosition position2 = JobPosition.builder()
                .name("Nauczyciel")
                .isMentalStress(false)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(true)
                .build();

        JobPosition job2 = JobInfo.addJobPosition(position2);

        assertNotNull(job2);

    }


    @Test
    void validationTest() {
        JobPosition position = JobPosition.builder()
                .name("Lekarz")
                .isMentalStress(false)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(true)
                .build();


        boolean res = JobInfo.validation(position);

        JobPosition position2 = JobPosition.builder()
                .name("Nauczyciel")
                .isMentalStress(true)
                .isBiologicalRisk(true)
                .isPhysicalRisk(true)
                .isChemicalRisk(false)
                .build();


        boolean res2 = JobInfo.validation(position2);

        assertFalse(res2);

    }
}