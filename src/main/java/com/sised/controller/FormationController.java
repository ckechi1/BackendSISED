package com.sised.controller;
import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.DemandeurFormation;
import com.sised.model.Formation;
import com.sised.service.DemandeurService;
import com.sised.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/SISED/demandeur/{demandeurId}")
public class FormationController {

    @Autowired
    private DemandeurService demandeurService;

    @Autowired
    private FormationService formationService;

    @GetMapping("/formation")
    public List<Formation> gellAllFormationBydemandeurId() {
        return formationService.getFormations();
    }

    @PostMapping("/formation")
    public Formation createFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                     @Valid @RequestBody Formation formation)
            throws ResourceNotFoundException {
        return demandeurService.getDemandeur(demandeurId).map(demandeur -> {
            formation.setDemandeur(demandeur);
            return formationService.saveFormation(formation);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurId" + demandeurId + "not found"));

    }

    @PutMapping("/formation/{formationId}")
    public Formation updateFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                     @PathVariable(value = "formationId") Long formationId,
                                     @Valid @RequestBody Formation formationRequest) throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {
            throw new ResourceNotFoundException("demandeurId " + demandeurId + " Not found ");

        } else
            return formationService.getFormation(formationId).map(formation -> {
                formation.setNom(formationRequest.getNom());
                formation.setPays(formationRequest.getPays());
                formation.setSpecialite(formationRequest.getSpecialite());
                formation.setDateObtention(formationRequest.getDateObtention());
                formation.setEtablissement(formationRequest.getEtablissement());
                return formationService.saveFormation(formation);
            }).orElseThrow(() -> new ResourceNotFoundException(" formationId " + formationId + "Not Found "));

    }

    @DeleteMapping("/formation/{formationId}")
    public ResponseEntity<?> deleteFormation(@PathVariable(value = "formationId") Long formationId,
                                             @PathVariable(value = "demandeurId") Long demandeurId) throws ResourceNotFoundException {
        return formationService.getFormationByDemandeurId(formationId, demandeurId).map(formation -> {
            formationService.deleteFormationById(formationId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("formation not found with id " + formationId + "demandeur" + demandeurId + "demandeurId"));

    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<Formation> getFormationByIdDemandeurById(@PathVariable(value = "formationId") Long formationId,
                                                                   @PathVariable(value = "demandeurId") Long demandeurId)
            throws ResourceNotFoundException {
        Formation formation = formationService.getFormationByDemandeurId(formationId, demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found with Id " + formationId + " and demandeur Id  " + demandeurId));
        return ResponseEntity.ok().body(formation);
    }

    //////////////// DemandeurFormation Controller //////////////////


    @GetMapping("/formation/{formationId}/demandeurformation/{demandeurformationId}")
    public ResponseEntity<DemandeurFormation> getFormationByIdDemandeurByIdFormationID(@PathVariable(value = "demandeurformationId") Long demandeurformationId,
                                                                                       @PathVariable(value = "demandeurId") Long demandeurId,
                                                                                       @PathVariable(value = "formationId") Long formationId)
            throws ResourceNotFoundException {
        DemandeurFormation demandeurformation = formationService.getFormationDemandeurandDemandeurFormationId(demandeurformationId, formationId, demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found with Id " + formationId + " and demandeur Id  " + demandeurId + " and demandeurformation Id  " + demandeurformationId));
        return ResponseEntity.ok().body(demandeurformation);
    }

    @GetMapping("/formation/{formationId}/demandeurformation")
    public List<DemandeurFormation> gellAlldemandeurFormationbyformationID() {
        return formationService.getDemandeurFormations();
    }

    @PostMapping("/formation/{formationId}/demandeurformation")
    public DemandeurFormation createDemandeurFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                                       @PathVariable(value = "formationId") Long formationId,
                                                       @Valid @RequestBody DemandeurFormation demandeurformation)
            throws ResourceNotFoundException {
        demandeurService.getDemandeur(demandeurId).map(demandeur -> {
            demandeurformation.setDemandeur(demandeur);
            return demandeurService.getDemandeur(demandeurId);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurID" + demandeurId + "not found"));

        formationService.getFormation(formationId).map(formation -> {
            demandeurformation.setFormation(formation);
            return formationService.getFormation(formationId);
        }).orElseThrow(() -> new ResourceNotFoundException("formation" + formationId + "not found"));
        return formationService.saveDemandeurFormation(demandeurformation);
    }

    @PutMapping("/formation/{formationId}/demandeurformation/{demandeurformationId}")
    public DemandeurFormation UpdateDemandeurFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                                       @PathVariable(value = "formationId") Long formationId,
                                                       @PathVariable(value = "demandeurformationId") Long demandeurformationId,
                                                       @Valid @RequestBody DemandeurFormation demaformaRequest)
            throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {

            throw new ResourceNotFoundException("demandeurId " + demandeurId + " Not found  ");

        } else if (!formationService.getFormationByIdIfExists(formationId)) {

            throw new ResourceNotFoundException("formation Id " + formationId + "Not found ");

        } else return formationService.getDemandeurFormationId(demandeurformationId).map(demandeurFormation -> {
            demandeurFormation.setPromotion(demaformaRequest.getPromotion());
            demandeurFormation.setMention(demaformaRequest.getMention());
            demandeurFormation.setPromotion(demaformaRequest.getPromotion());
            return formationService.saveDemandeurFormation(demandeurFormation);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurformationId" + demandeurformationId + " Not Found "));

    }

    @DeleteMapping("/formation/{formationId}/demandeurformation/{demandeurformationId}")
    public ResponseEntity<?> deleteDemandeurFormationId(@PathVariable(value = "demandeurId") Long demandeurId,
                                                         @PathVariable(value = "formationId") Long formationId,
                                                         @PathVariable(value = "demandeurformationId") Long demandeurformationId)
                                                         throws ResourceNotFoundException {
        return  formationService.getFormationDemandeurandDemandeurFormationId(demandeurformationId, formationId, demandeurId).map(demandeurFormation -> {
             formationService.deleteDemandeurformationById(demandeurformationId);
             return ResponseEntity.ok().build();
         }).orElseThrow(()-> new ResourceNotFoundException("demandeurformation Id " + demandeurformationId + " Not found and " + "demandeur Id " +  demandeurId +  " Not found and " + " formationId " + formationId + " Not found"  ));
    }

}

