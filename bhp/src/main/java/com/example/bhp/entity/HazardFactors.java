package com.example.bhp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hazard_factors")
public class HazardFactors {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="hazard", nullable = false)
    private String hazard = "";

    @Column(name="cause_of_hazard", nullable = false)
    private String causeOfHazard = "";

    @Column(name="probability", nullable = false)
    private Double probability;

    @Column(name="rank_of_effects", nullable = false)
    private Double rank;

    @Column(name="risk", nullable = false)
    private Double risk;

    @Column(name="preventive_actions", nullable = true)
    private String actions;

    @Column(name="probability_after_preventive_actions", nullable = false)
    private Integer probabilityAfterPreventiveActions;

    @Column(name="rank_of_effects_after_preventive_actions", nullable = false)
    private Integer rankAfterPreventiveActions;

    @Column(name="risk_after_preventive_actions", nullable = false)
    private Integer riskAfterPreventiveActions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_assessment_id")
    private RiskAssessment riskAssessment;

}