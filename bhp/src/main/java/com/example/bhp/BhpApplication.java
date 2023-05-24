package com.example.bhp;

import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RegistryOfAccidents;
import com.example.bhp.entity.TrainingRegister;
import com.example.bhp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class BhpApplication implements CommandLineRunner
{

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	JobRepository jobRepository;

	@Autowired
	RegisterOfAccidentsRepository registerOfAccidentsRepository;

	@Autowired
	RiskRepository riskRepository;

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	HazardFactorsRepository repository;


	public static void main(String[] args) {

		SpringApplication.run(BhpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		JobPosition jobPosition1 = JobPosition.builder()
				.name("C++ Developer")
				.isMentalStress(true)
				.build();

		JobPosition jobPosition2 = JobPosition.builder()
				.name("Data Analyst")
				.isMentalStress(true)
				.build();

		JobPosition jobPosition3 = JobPosition.builder()
				.name("Java Developer")
				.isMentalStress(true)
				.build();

		jobPosition1 = jobRepository.save(jobPosition1);
		jobPosition2 = jobRepository.save(jobPosition2);
		jobPosition3 = jobRepository.save(jobPosition3);

		Employees employee1 = Employees.builder()
				.firstName("Karolina")
				.lastName("Maciejewska")
				.email("karma0510@gmail.com")
				.date(LocalDate.of(2022, 1, 1))
				.lastTrainingDate(LocalDate.of(2022, 1, 2))
				.nrOfDepartment(9)
				.jobPosition(jobPosition2)
				.build();

		employeeRepository.save(employee1);
		System.out.println("A");

		Employees employee2 = Employees.builder()
				.firstName("Michal")
				.lastName("Sokolowski")
				.email("michael@gmail.com")
				.date(LocalDate.of(2021, 10, 1))
				.lastTrainingDate(LocalDate.of(2021, 10, 2))
				.nrOfDepartment(9)
				.jobPosition(jobPosition3)
				.build();

		employeeRepository.save(employee2);

		Employees employee3 = Employees.builder()
				.firstName("Mateusz")
				.lastName("Maciejewski")
				.email("matmac@gmail.com")
				.date(LocalDate.of(2020, 10, 1))
				.lastTrainingDate(LocalDate.of(2020, 10, 2))
				.nrOfDepartment(3)
				.jobPosition(jobPosition1)
				.build();

		employeeRepository.save(employee3);

		Employees employee4 = Employees.builder()
				.firstName("Tom")
				.lastName("Riddle")
				.date(LocalDate.of(2020, 10, 1))
				.lastTrainingDate(LocalDate.of(2020, 10, 2))
				.nrOfDepartment(4)
				.jobPosition(jobPosition2)
				.build();


		employeeRepository.save(employee4);

		RegistryOfAccidents accident = RegistryOfAccidents.builder()
				.accident_id(12023)
				.date(LocalDate.of(2023,02, 02))
				.isAccident(true)
				.number(2)
				.employees(new ArrayList<>())
				.responsibleBranch(9)
				.protocole("01-2023")
				.place("biuro")
				.type(RegistryOfAccidents.Accident_priority.COLLECTIVE)
				.build();

		RegistryOfAccidents accident2 = RegistryOfAccidents.builder()
				.accident_id(22023)
				.date(LocalDate.of(2023,05, 02))
				.isAccident(true)
				.place("Hogwart")
				.employees(new ArrayList<>())
				.responsibleBranch(4)
				.protocole("02-2023")
				.type(RegistryOfAccidents.Accident_priority.FATAL)
				.number(1)
				.build();

		System.out.println(accident.getEmployees());
		System.out.println(accident2.getEmployees());


		//accident.addEmployee(employee1);
		//accident.addEmployee(employee3);

		//System.out.println("lll");


		//accident2.addEmployee(employee4);

		employee1.getRegister_of_accidents().add(accident);
		accident.getEmployees().add(employee1);

		employee3.getRegister_of_accidents().add(accident);
		accident.getEmployees().add(employee3);

		employee2.getRegister_of_accidents().add(accident2);
		accident2.getEmployees().add(employee2);


		registerOfAccidentsRepository.save(accident);
		registerOfAccidentsRepository.save(accident2);

		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);

		TrainingRegister t1 = TrainingRegister.builder()
				.first_date(LocalDate.of(2022, 01, 01))
				.date_exam(LocalDate.of(2022, 01, 05))
				.build();

		TrainingRegister t2  = TrainingRegister.builder()
				.first_date(LocalDate.of(2023, 02, 02))
				.date_exam(LocalDate.of(2023, 01, 05))
				.build();


		t1.getEmployees().add(employee1);
		t1.getEmployees().add(employee2);
		employee1.getPeriodic_training_register().add(t1);
		employee2.getPeriodic_training_register().add(t1);

		trainingRepository.save(t1);
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);





	}
}
