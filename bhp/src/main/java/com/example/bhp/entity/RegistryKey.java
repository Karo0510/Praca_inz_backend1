package com.example.bhp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class RegistryKey implements Serializable
{
    private static final long serialVersionUID = -6792570972318700239L;
    @Column(name="accident_id", nullable = false)
    private long accidentId;

    @Column(name="responsible_branch", nullable = true)
    private Integer responsibleBranch;

}
