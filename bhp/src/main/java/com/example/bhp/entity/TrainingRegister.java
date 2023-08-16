package com.example.bhp.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Table(name = "periodic_training_register")
@NamedQueries(
        {
                @NamedQuery(name="trainings", query="Select t from TrainingRegister t")
        }
)

public class TrainingRegister implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long training_id;

    @Column(name="periodic_training_date", nullable = true) //data ostatniego szkolenia okresowego
    private LocalDate first_date;

    @Column(name="periodic_training_exam_date", nullable = true) //data egzaminu szkolenia okresowego
    private LocalDate date_exam;

    @Builder.Default
    @ManyToMany(mappedBy = "periodic_training_register", fetch = FetchType.EAGER)
    private List<Employees> employees = new ArrayList<>();

}
