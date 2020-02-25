package com.sised.service;

import com.sised.model.Demandeur;
import com.sised.repository.DemandeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeurService {

    @Autowired
    DemandeurRepository demandeurRepository;

    public Page<Demandeur> getDemandeursPagination(Pageable pageable){
        return demandeurRepository.findAll(pageable);
    }

    public Optional<Demandeur> getDemandeur(Long id){
        return demandeurRepository.findById(id);
    }

    public Demandeur saveDemandeur(Demandeur demandeur){
        return demandeurRepository.save(demandeur);
    }

    public void deleteDemandeur(Demandeur demandeur) {
        demandeurRepository.delete(demandeur);
    }

    public boolean getDemanderbyIdIfExists(Long id){
        return demandeurRepository.existsById(id);
    }

    public List<Demandeur> getDemandeurs() {
        return demandeurRepository.findAll();
    }
}
