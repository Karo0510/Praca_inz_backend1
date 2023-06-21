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
    private Long id;

    @Column(name="job_position_name", nullable = false, unique = true)
    private String name;

    @Column(name="is_chemical_risk", nullable = false)
    private boolean isChemicalRisk = false;

    @Column(name="is_biological_risk", nullable = false)
    private boolean isBiologicalRisk = false;

    @Column(name="is_physical_risk", nullable = false)
    private boolean isPhysicalRisk = false;

    @Column(name="is_mental_stress", nullable = false)
    private boolean isMentalStress = false;

    @Builder.Default
    @JsonBackReference(value = "**")
    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RiskAssessment> riskAssessments = new ArrayList<>();

    @Builder.Default
    @JsonBackReference(value = "**")
    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL)
    private List<Employees> employees = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobPosition)) return false;

        JobPosition that = (JobPosition) o;

        if (isChemicalRisk != that.isChemicalRisk) return false;
        if (isBiologicalRisk != that.isBiologicalRisk) return false;
        if (isPhysicalRisk != that.isPhysicalRisk) return false;
        if (isMentalStress != that.isMentalStress) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (isChemicalRisk ? 1 : 0);
        result = 31 * result + (isBiologicalRisk ? 1 : 0);
        result = 31 * result + (isPhysicalRisk ? 1 : 0);
        result = 31 * result + (isMentalStress ? 1 : 0);
        return result;
    }
}
