package com.example.bhp.entity;

import jakarta.persistence.*;
import lombok.*;


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

    @Column(name="isChemicalRisk", nullable = false)
    private boolean isChemicalRisk = false;

    @Column(name="isBiologicalRisk", nullable = false)
    private boolean isBiologicalRisk = false;

    @Column(name="isPhysicalRisk", nullable = false)
    private boolean isPsysicalRisk = false;

    @Column(name="isMentalStress", nullable = false)
    private boolean isMentalStress = false;

    @OneToOne(mappedBy = "job_position")
    private OccupationalRiskAssesment riskAssesment;

}
