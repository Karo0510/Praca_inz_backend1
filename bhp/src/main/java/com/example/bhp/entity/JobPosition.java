package com.example.bhp.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job_position")

public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_position_id", nullable = false)
    private long id;

    @Column(name="job_position_name", nullable = false, unique = true)
    private String name;

    @Column(name="is_chemical_risk", nullable = false)
    private boolean isChemicalRisk = false;

    @Column(name="is_biological_risk", nullable = false)
    private boolean isBiologicalRisk = false;

    @Column(name="is_physical_risk", nullable = false)
    private boolean isPsysicalRisk = false;

    @Column(name="is_mental_stress", nullable = false)
    private boolean isMentalStress = false;

    @Builder.Default
    @JsonBackReference(value = "**")
    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL)
    private List<RiskAssessment> riskAssessments = new ArrayList<>();

    @Builder.Default
    @JsonBackReference(value = "**")
    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL)
    private List<Employees> employees = new ArrayList<>();




}
