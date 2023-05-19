package com.example.bhp.entity;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
@Table(name = "register_of_accidents")

public class RegistryOfAccidents implements Serializable {

    enum Accident_priority {
        LIGHT,
        HEAVY,
        FATAL,
        COLLECTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;

    @Column(name="accident_id", nullable = false, unique = true)
    private long accident_id;

    @Column(name="date_of_accidents", nullable = false)
    private LocalDate date = LocalDate.of(2022, 1, 1);

    @Column(name="type_of_accidents", nullable = false) //lekki, ciezki, smiertelny, zbiorowy
    private Accident_priority type;

    @Column(name="place_of_accident", nullable = false)
    private String place = "";

    @Column(name="number_of_victims", nullable = false)
    private Integer number = 0;

    @Column(name="number_of_accidental_protocole", nullable = false, unique = true)
    private String protocole;

    @Column(name="is_recognized_as_accident", nullable = false)
    private boolean isAccident = false;

    @ManyToMany(mappedBy = "register_of_accidents", fetch = FetchType.EAGER)
    private List<Employees> employees;

    @Column(name="responsible_branch", nullable = true)
    private Integer responsibleBranch;

}
