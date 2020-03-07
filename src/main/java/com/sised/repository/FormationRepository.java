package com.sised.repository;

import com.sised.model.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation , Long> {
    //List<Formation> findByDemandeurId(Long demandeurId);
  //  Optional<Formation> findByIdAndDemandeurId(Long formationId, Long demandeurId);
   // Optional<Formation> findByIdAndDemandeurIdAndDemandeurFormationId(Long formationId, Long demandeurId , Long demandeurformationid );
  //  Page<Formation> findByDemandeurId(Long demandeurId ,Pageable pageable );
    List<Formation> findAll();

    Page<Formation> findAll(Pageable pageable );

}
