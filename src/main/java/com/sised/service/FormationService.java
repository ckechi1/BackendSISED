package com.sised.service;

import com.sised.model.DemandeurFormation;
import com.sised.model.Formation;
import com.sised.repository.DemandeurFormationRepository;
import com.sised.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    DemandeurFormationRepository demandeurFormationRepository;

    public List<Formation> getFormations(){

        return formationRepository.findAll();
    }

    public Optional<Formation> getFormation(long id ){
        return formationRepository.findById(id);
    }

    public Formation saveFormation(Formation formation){
        return formationRepository.save(formation);
    }

    public Optional<Formation> getFormationByDemandeurId(Long formationId, Long demandeurId) {
        return formationRepository.findByIdAndDemandeurId(formationId,demandeurId);
    }  // get a demandeur id with the formation id

    public void deleteFormationById(Long formationId) {
         formationRepository.deleteById(formationId);
    }

    public Optional<DemandeurFormation> getFormationDemandeurandDemandeurFormationId(Long demandeurformationid, Long formationId, Long demandeurId ){
        return demandeurFormationRepository.findByIdAndFormation_idAndDemandeur_id( demandeurformationid, formationId, demandeurId);
    }

    public List<DemandeurFormation> getDemandeurFormations() {
        return demandeurFormationRepository.findAll();
    }
}
