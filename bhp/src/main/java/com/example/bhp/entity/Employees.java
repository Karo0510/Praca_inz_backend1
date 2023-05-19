package com.example.bhp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EmployeesPeriodicTrainings",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private List<TrainingRegister> periodic_training_register;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EmployeesAccident",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "accident_id")
    )
    private List<RegistryOfAccidents> register_of_accidents;

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


}

