package com.sised.service;

import com.sised.model.Formation;
import com.sised.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    FormationRepository formationRepository;


    public List<Formation> getFormations(){
        return formationRepository.findAll();
    }

    public Optional<Formation> getFormation(long id ){
        return formationRepository.findById(id);
    }

    public Formation saveFormation(Formation formation){
        return formationRepository.save(formation);
    }
}
