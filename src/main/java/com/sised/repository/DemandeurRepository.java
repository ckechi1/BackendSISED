package com.sised.repository;

import com.sised.model.Demandeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeurRepository extends JpaRepository<Demandeur, Long>{
    Page<Demandeur> findAll(Pageable pageable);
}
