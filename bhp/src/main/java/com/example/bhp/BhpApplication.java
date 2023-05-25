package com.example.bhp;

import com.example.bhp.entity.*;
import com.example.bhp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

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
	RiskAssessmentRepository riskRepository;

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	HazardFactorsRepository repository;


	public static void main(String[] args) {

		SpringApplication.run(BhpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*JobPosition jobPosition1 = JobPosition.builder()
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


		RiskAssessment r1 = RiskAssessment.builder()
				.date(LocalDate.now())
				.nrOfDepartment(9)
				.build();


		RiskAssessment r2 = RiskAssessment.builder()
				.date(LocalDate.of(2020, 05, 24))
				.nrOfDepartment(9)
				.build();

		jobPosition1.getRiskAssessments().add(r1);
		r1.setJobPosition(jobPosition1);

		jobPosition1.getRiskAssessments().add(r2);
		r2.setJobPosition(jobPosition1);

		jobRepository.save(jobPosition1);
		riskRepository.save(r1);
		riskRepository.save(r2);

		HazardFactors h1 = HazardFactors.builder()
				.hazard("Oparzenie 2 stopnia")
				.causeOfHazard("Noszenie goracego kubka z herbata")
				.probability(2.0)
				.rank(2.0)
				.risk(2.0)
				.build();

		HazardFactors h2 = HazardFactors.builder()
				.hazard("Porazenie pradem")
				.causeOfHazard("Uszkodzenie przewodow")
				.probability(2.0)
				.rank(3.0)
				.risk(3.0)
				.actions("Zwracanie uwagi na stan sprzetu. Zglaszanie incydentow do przelozonego")
				.probabilityAfterPreventiveActions(1.0)
				.rankAfterPreventiveActions(3.0)
				.probabilityAfterPreventiveActions(2.0)
				.build();

		HazardFactors h3 = HazardFactors.builder()
				.hazard("Kopniecie kolegi")
				.causeOfHazard("Wkurzenie kolegi")
				.probability(1.0)
				.rank(1.0)
				.risk(1.0)
				.build();

		r1.getFactors().add(h1);
		h1.setRiskAssessment(r1);

		r1.getFactors().add(h2);
		h2.setRiskAssessment(r1);

		r2.getFactors().add(h1);
		h1.setRiskAssessment(r2);

		r2.getFactors().add(h2);
		h2.setRiskAssessment(r2);

		r2.getFactors().add(h3);
		h3.setRiskAssessment(r2);

		repository.save(h1);
		repository.save(h2);
		repository.save(h3);

		riskRepository.save(r1);
		riskRepository.save(r2);*/


	}
}
