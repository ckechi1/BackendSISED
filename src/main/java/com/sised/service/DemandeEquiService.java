package com.sised.service;

import com.sised.model.DemandeEquivalence;
import com.sised.model.StatusDemande;
import com.sised.repository.DemandeEquivalenceRepository;
import com.sised.repository.StatusDemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeEquiService {

    @Autowired
    DemandeEquivalenceRepository demandeEquivalenceRepository;

    @Autowired
    StatusDemandeRepository statusDemandeRepository;

    public List<DemandeEquivalence> getDemandeEquivalences(){
        return demandeEquivalenceRepository.findAll();
    }

    public Optional<DemandeEquivalence> getDemandeEquivalence(Long id ){
        return demandeEquivalenceRepository.findById(id);
    }

    public DemandeEquivalence saveDemandeEquivalence(DemandeEquivalence demande){
        return demandeEquivalenceRepository.save(demande);
    }

    public void  deleteDemandeEquivalenceById(Long id){
        demandeEquivalenceRepository.deleteById(id);
    }

    public Optional<DemandeEquivalence> getDemandeEquivalenceByIdFormationId(Long demandeId , Long demandeurId  ){
         return  demandeEquivalenceRepository.findByIdAndDemandeur_Id(demandeId , demandeurId);
    }

    public boolean getDemandeEquivalenceByIdIfExists(Long id){
        return demandeEquivalenceRepository.existsById(id);
    }

                      ////////////// statusdemande services /////////////////

    public List<StatusDemande> getStatusDemandes(){
        return statusDemandeRepository.findAll();
    }

    public Optional<StatusDemande> getStatusdemandeById(Long id ){
        return statusDemandeRepository.findById(id);
    }

    public StatusDemande saveStatusdemande(StatusDemande statusdemande){
        return statusDemandeRepository.save(statusdemande);
    }

    public Optional<StatusDemande> getDemandeEquivalenceAndStatusById(Long statusdemandeId, Long demandeEquivalenceId){
        return statusDemandeRepository.findByIdAndDemandeEquivalence_Id(statusdemandeId,demandeEquivalenceId);
    }

    public void deleteStatusDemande(Long statusdemandeid){
        statusDemandeRepository.deleteById(statusdemandeid);
    }
}

