package com.sised.controller;

import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.Formation;
import com.sised.repository.DemandeurRepository;
import com.sised.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/SISED/demandeur")
public class FormationController {

    @Autowired
    private DemandeurRepository demandeurRepository;
    @Autowired
    private FormationRepository formationRepository;

    @GetMapping("/{demandeurId}/formation")
    public Optional<Formation> gellAllFormationBydemandeurId(@PathVariable(value = "demandeurId") Long demandeurId) {
        return formationRepository.findById(demandeurId);

    }

    @PostMapping("/{demandeurId}/formation")
    public Formation createFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                     @Valid @RequestBody Formation formation) throws ResourceNotFoundException {
        return demandeurRepository.findById(demandeurId).map(demandeur -> {
            formation.setDemandeur(demandeur);
            return formationRepository.save(formation);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurId" + demandeurId + "not found"));

    }

    @PutMapping("/{demandeurId}/formation/{formationId}")
    public Formation updateFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                     @PathVariable(value = "formationId") Long formationId,
                                     @Valid @RequestBody Formation formationRequest) throws ResourceNotFoundException {
        if (!demandeurRepository.existsById(demandeurId)) {
            throw new ResourceNotFoundException("demandeurId " + demandeurId + " Not found ");

        } else
            return formationRepository.findById(formationId).map(formation -> {
                formation.setNom(formationRequest.getNom());
                formation.setPays(formationRequest.getPays());
                formation.setSpecialite(formationRequest.getSpecialite());
                formation.setDateObtention(formationRequest.getDateObtention());
                formation.setEtablissement(formationRequest.getEtablissement());
                return formationRepository.save(formation);
            }).orElseThrow(() -> new ResourceNotFoundException(" formationId " + formationId + "Not Found "));

    }

    @DeleteMapping("/{demandeurId/formation/{formationId}")
    public ResponseEntity<?> deleteFormation(@PathVariable(value = "formationId") Long formationId,
                                             @PathVariable(value = "demandeurId") Long demandeurId) throws ResourceNotFoundException {
        return formationRepository.findByIdAndPostId(formationId, demandeurId).map(formation -> {
            formationRepository.deleteById(formationId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("formation not found with id " + formationId + "demandeur" + demandeurId + "demandeurId"));

    }

    @GetMapping("/{demandeurId/formation/{formationId}")
    public ResponseEntity<Object> getFormation(@PathVariable(value = "formationId") Long formationId,
                                               @PathVariable(value = "demandeurId") Long demandeurId) throws ResourceNotFoundException {
        return formationRepository.findByIdAndPostId(formationId, demandeurId).map(formation -> {
            formationRepository.findById(formationId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("formation not found with id " + formationId + "demandeur" + demandeurId + "demandeurId"));

    }
}