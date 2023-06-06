package com.example.bhp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "risk_assessment")
public class RiskAssessment {
    //XXX: zrobic historyczna wersje oceny ryzyka -> relacja ze stanowiskiem: OneToMany
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;

    @Column(name="last_update_date", nullable = false) //XXX: change to create_date
    private LocalDate date;

    @Column(name="nr_of_department", nullable = true)
    private Integer nrOfDepartment;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;


    @OneToMany(mappedBy = "riskAssessment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    @JsonBackReference(value = "**")
    List<HazardFactors> factors = new ArrayList<>();


}
