package com.example.bhp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "risk")

public class OccupationalRiskAssesment {
    //XXX: zrobic historyczna wersje oceny ryzyka -> relacja ze stanowiskiem: OneToMany
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;

    @OneToOne
    private JobPosition job_position;

    @Column(name="dangerous", nullable = false)
    private String dangerous = "";

    @Column(name="cause_of_dangerous", nullable = false)
    private String causeOfDangerous = "";

    @Column(name="probability", nullable = false)
    private Integer probability;

    @Column(name="rank_of_effects", nullable = false)
    private Integer rank;

    @Column(name="risk", nullable = false)
    private Integer risk;

    @Column(name="preventive_actions", nullable = true)
    private String actions;

    @Column(name="probability_after_preventive_actions", nullable = false)
    private Integer probabilityAfterPreventiveActions;

    @Column(name="rank_of_effects_after_preventive_actions", nullable = false)
    private Integer rankAfterPreventiveActions;

    @Column(name="risk_after_preventive_actions", nullable = false)
    private Integer riskAfterPreventiveActions;

    @Column(name="last_update_date", nullable = false)
    private LocalDate date;

    @Column(name="nr_of_department", nullable = true)
    private Integer nrOfDepartment;




}
