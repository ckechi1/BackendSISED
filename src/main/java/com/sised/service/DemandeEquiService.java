package com.sised.service;

import com.sised.model.DemandeEquivalence;
import com.sised.repository.DemandeEquivalenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeEquiService {

    @Autowired
    DemandeEquivalenceRepository demandeEquivalenceRepository;

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

}
