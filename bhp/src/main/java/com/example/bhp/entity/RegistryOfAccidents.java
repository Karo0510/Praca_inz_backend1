package com.example.bhp.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
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

@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "register_of_accidents")

public class RegistryOfAccidents implements Serializable {

    //XXX: dodac przyczyne wypadku, co sprawdzono, dzialania korygujace, sprobować poprawić enum na string

    public enum Accident_priority {
        LIGHT,
        HEAVY,
        FATAL,
        COLLECTIVE
    }

    @EmbeddedId
    RegistryKey key;

    @Column(name="date_of_accidents", nullable = false)
    private LocalDate date = LocalDate.of(2022, 1, 1);

    //@Enumerated(EnumType.STRING)
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

    @Builder.Default
    @ManyToMany(mappedBy = "register_of_accidents")
    private List<Employees> employees = new ArrayList<>();

    public void addEmployee(Employees emp) {
        employees.add(emp);
        emp.getRegister_of_accidents().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistryOfAccidents)) return false;

        RegistryOfAccidents accidents = (RegistryOfAccidents) o;

        if (isAccident != accidents.isAccident) return false;
        if (key != null ? !key.equals(accidents.key) : accidents.key != null) return false;
        if (date != null ? !date.equals(accidents.date) : accidents.date != null) return false;
        if (type != accidents.type) return false;
        if (place != null ? !place.equals(accidents.place) : accidents.place != null) return false;
        if (number != null ? !number.equals(accidents.number) : accidents.number != null) return false;
        return protocole != null ? protocole.equals(accidents.protocole) : accidents.protocole == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (protocole != null ? protocole.hashCode() : 0);
        result = 31 * result + (isAccident ? 1 : 0);
        return result;
    }
}
