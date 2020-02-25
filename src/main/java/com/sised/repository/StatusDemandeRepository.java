package com.sised.repository;

import com.sised.model.Formation;
import com.sised.model.StatusDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande , Long > {
    List<StatusDemande> findByDemandeEquivalence_Id(Long DemandeEquivalence_Id);
    Optional<StatusDemande> findByIdAndDemandeEquivalence_Id(Long statusdemandeId, Long demandeEquivalenceId);
}
