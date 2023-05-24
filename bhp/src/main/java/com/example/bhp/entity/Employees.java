package com.example.bhp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//lombok - biblioteka wytwarzajaca ukryte gettery, settery, buildery, konstruktory
//builder - design pattern -- przypomnieć/znalezc informacje
//persistence - wpisywanie obiektow do bazy danych
//entity - encja - ten obiekt mozna wpisac do bazy danych
//table - tabela employee??? (sprawdzic)

//znac metody http

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    //XXX: dodac do klasy pesel, data konca pracy, oraz data wpisu

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    @JoinTable(name = "EmployeesPeriodicTrainings",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private List<TrainingRegister> periodic_training_register = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    @JoinTable(name = "EmployeesAccident",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "accident_id")
    )
    private List<RegistryOfAccidents> register_of_accidents = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id", nullable = false)
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = true)
    private String email;

    @Column(name="first_work_day", nullable = false)
    private LocalDate date;

    @Column(name="last_day_of_introductory_training", nullable = true) //XXX: ostatni dzien szkolenia wstepnego zmienic na false
    private LocalDate lastTrainingDate = LocalDate.of(2022, 01, 01);

    @Column(name="nr_of_department", nullable = true)
    private Integer nrOfDepartment;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", nullable = false)
    private JobPosition jobPosition;


}

