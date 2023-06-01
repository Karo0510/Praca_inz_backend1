package com.example.bhp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RegistryKey implements Serializable {
    private static final long serialVersionUID = -6792570972318700239L;
    @Column(name="accident_id", nullable = false)
    private long accident_id;

    @Column(name="responsible_branch", nullable = true)
    private Integer responsibleBranch;

}
