package com.sised.repository;

import com.sised.model.StatusDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande , Long > {

    Optional<StatusDemande> findByIdAndDemandeEquivalence_Id(Long statusdemandeId, Long demandeEquivalenceId);
}
