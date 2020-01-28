package com.sised.repository;

import com.sised.model.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormationRepository extends JpaRepository<Formation , Long> {
    Page<Formation> findByDemandeurId(Long demandeurId, Pageable pageable);
    Optional<Formation> findByIdAndDemandeurId(Long formationId, Long demandeurId);
}
