package com.sised.repository;

import com.sised.model.DemandeEquivalence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeEquivalenceRepository extends JpaRepository<DemandeEquivalence , Long > {
    List <DemandeEquivalence> findByDemandeurId(Long DemandeurId);
 // Optional<DemandeEquivalence> findyIdDemandeEquivalenceAndDemandeurById(Long demandeId, Long demandeurId);
   // List<DemandeEquivalence> findAllByDateDepot(String dateDepot);
    Optional<DemandeEquivalence> findByIdAndDemandeur_Id(Long id, Long idDemandeur);
    Page<DemandeEquivalence> findByDemandeurId(Long DemandeurId , Pageable pageable);
}
