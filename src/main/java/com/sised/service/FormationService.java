package com.sised.service;
import com.sised.model.Demandeur;
import com.sised.model.DemandeurFormation;
import com.sised.model.Formation;
import com.sised.repository.DemandeurFormationRepository;
import com.sised.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class FormationService {

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    DemandeurFormationRepository demandeurFormationRepository;

     public List<Formation> getAllFormation() {
        return  formationRepository.findAll();
    }

    public Page<Formation> getFormationsPagination(Pageable pageable ){
        return formationRepository.findAll(pageable);
    }

    public Optional<Formation> getFormation(long id ){
        return formationRepository.findById(id);
    }

    public Formation saveFormation(Formation formation){
        return formationRepository.save(formation);
    }

//    public Optional<Formation> getFormationByDemandeurId(Long formationId, Long demandeurId) {
//        return formationRepository.findByIdAndDemandeurId(formationId,demandeurId);
//    }  // get a demandeur id with the formation id

    public void deleteFormationById(Long formationId) {
         formationRepository.deleteById(formationId);
    }

    public boolean getFormationByIdIfExists(Long id ){
        return formationRepository.existsById(id);
    }

                              ////////////// DemandeurFormation services /////////////////
     public Page <DemandeurFormation> getDemandeurFormationPagination( Long demandeurId , Pageable pageable){
        return demandeurFormationRepository.findByDemandeur_id(demandeurId, pageable);
    }

    public Optional<DemandeurFormation> getFormationDemandeurandDemandeurFormationId(Long demandeurformationid, Long formationId, Long demandeurId ){
        return demandeurFormationRepository.findByIdAndFormation_idAndDemandeur_id( demandeurformationid, formationId, demandeurId);
    }

    public List<DemandeurFormation> getDemandeurFormations() {
        return demandeurFormationRepository.findAll();
    }

    public DemandeurFormation saveDemandeurFormation(DemandeurFormation demandeurformation) {
        return demandeurFormationRepository.save(demandeurformation);
    }

    public Optional<DemandeurFormation> getDemandeurFormationId(Long id ){
        return demandeurFormationRepository.findById(id);
    }

    public void deleteDemandeurformationById(Long id ) {
        demandeurFormationRepository.deleteById(id);
    }

    public Optional<DemandeurFormation>getDemandeurAndDemandeurFormationId(Long demandeurformationId, Long demandeurId) {
         return demandeurFormationRepository.findByIdAndDemandeur_id(demandeurformationId, demandeurId);
    }
}
