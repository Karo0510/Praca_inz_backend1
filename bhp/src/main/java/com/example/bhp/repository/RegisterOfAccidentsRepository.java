package com.example.bhp.repository;

import com.example.bhp.entity.RegistryKey;
import com.example.bhp.entity.RegistryOfAccidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisterOfAccidentsRepository extends JpaRepository<RegistryOfAccidents, Long>
{
    @Query("select r from RegistryOfAccidents r where r.key.responsibleBranch = ?1")
    public List<RegistryOfAccidents> findByKeyResponsibleBranch(Integer branch);

    @Query("select r from RegistryOfAccidents r where r.key.accidentId = ?1")
    public List<RegistryOfAccidents> findByKeyAccidentId(Long id);

    @Query("select r from RegistryOfAccidents r where r.key.responsibleBranch = ?1 and r.key.accidentId = ?2")
    public RegistryOfAccidents findByKeyResponsibleBranchAndKeyAccidentId(Integer branch, Long id);


}