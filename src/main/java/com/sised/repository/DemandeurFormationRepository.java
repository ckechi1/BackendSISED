package com.sised.repository;

import com.sised.model.Demandeur;
import com.sised.model.DemandeurFormation;
import com.sised.model.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeurFormationRepository extends JpaRepository<DemandeurFormation , Long > {
     List<DemandeurFormation> findByFormation_idAndDemandeur_id(Long formId , Long demanId );
     Optional<DemandeurFormation> findByIdAndFormation_idAndDemandeur_id(Long demandeurformationid, Long formationId, Long demandeurId );
     Page<DemandeurFormation> findByDemandeur_id( Long demandeurId , Pageable pageable);
    Optional<DemandeurFormation> findByIdAndDemandeur_id(Long demandeurformationId, Long demandeurId);
}
