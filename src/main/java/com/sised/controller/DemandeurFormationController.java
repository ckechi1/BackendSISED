package com.sised.controller;
import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.DemandeurFormation;
import com.sised.service.DemandeurService;
import com.sised.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/SISED/demandeur/{demandeurId}")
public class DemandeurFormationController {

    @Autowired
    private DemandeurService demandeurService;

    @Autowired
    private FormationService formationService;

    //////////////// DemandeurFormation Controller //////////////////
    @GetMapping("/demandeurFormation/{demandeurFormationId}/formation/{formationId}")
    public ResponseEntity<DemandeurFormation> getFormationByIdDemandeurByIdFormationID(@PathVariable(value = "demandeurFormationId") Long demandeurformationId,
                                                                                       @PathVariable(value = "demandeurId") Long demandeurId,
                                                                                       @PathVariable(value = "formationId") Long formationId)
                                                                                       throws ResourceNotFoundException {
        DemandeurFormation demandeurformation = formationService.getFormationDemandeurandDemandeurFormationId
                (demandeurformationId, formationId, demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found with Id " + formationId +  " and demandeur Id  " + demandeurId + " and demandeurformation Id  " + demandeurformationId));
        return ResponseEntity.ok().body(demandeurformation);
    }

    @GetMapping("/demandeurFormation")
    public Page<DemandeurFormation> getAllDemandeurFormation(@PathVariable(value = "demandeurId") Long demandeurId, Pageable pageable) {
        return formationService.getDemandeurFormationPagination(demandeurId , pageable);
    }

    @PostMapping("/demandeurFormation/{formationId}")
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

    @PutMapping("/demandeurFormation/{demandeurFormationId}/formation/{formationId}")
    public DemandeurFormation UpdateDemandeurFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                                       @PathVariable(value = "demandeurFormationId") Long demandeurformationId,
                                                       @PathVariable(value = "formationId" ) Long formationId,
                                                       @Valid @RequestBody DemandeurFormation demaformaRequest)
                                                       throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {

            throw new ResourceNotFoundException("demandeurId " + demandeurId + " Not found  ");

        } else if (!formationService.getFormationByIdIfExists(formationId)  ) {

            throw new ResourceNotFoundException("formation Id " + formationId + "Not found ");

        } else return formationService.getDemandeurFormationId(demandeurformationId).map(demandeurFormation -> {

            demandeurFormation.setPays(demaformaRequest.getPays());
            demandeurFormation.setPromotion(demaformaRequest.getPromotion());
            demandeurFormation.setMention(demaformaRequest.getMention());
            demandeurFormation.setEtablissement(demaformaRequest.getEtablissement());
            demandeurFormation.setDateObtention(demaformaRequest.getDateObtention());
            demandeurFormation.setNomFormation(demaformaRequest.getNomFormation());

            return formationService.saveDemandeurFormation(demandeurFormation);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurformationId" + demandeurformationId + " Not Found "));

    }



    @DeleteMapping("/demandeurFormation/{demandeurformationId}")
    public ResponseEntity<?> deleteDemandeurFormationId(@PathVariable(value = "demandeurId") Long demandeurId,
                                                        @PathVariable(value = "demandeurformationId") Long demandeurformationId)
                                                        throws ResourceNotFoundException {
       return   formationService.getDemandeurAndDemandeurFormationId(demandeurformationId, demandeurId).map(demandeurFormation -> {
             formationService.deleteDemandeurformationById(demandeurformationId);
             return ResponseEntity.ok().build();
         }).orElseThrow(()-> new ResourceNotFoundException("demandeurformation Id " + demandeurformationId + " Not found and " + "demandeur Id " +  demandeurId  ));
    }

    @PutMapping("/demandeurFormation/{demandeurFormationId}")
    public DemandeurFormation UpdateDemandeurFormation(@PathVariable(value = "demandeurId") Long demandeurId,
                                                       @PathVariable(value = "demandeurFormationId") Long demandeurformationId,
                                                       @Valid @RequestBody DemandeurFormation demaformaRequest)
            throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {

            throw new ResourceNotFoundException("demandeurId " + demandeurId + " Not found  ");

        } else return formationService.getDemandeurFormationId(demandeurformationId).map(demandeurFormation -> {
            demandeurFormation.setPays(demaformaRequest.getPays());
            demandeurFormation.setPromotion(demaformaRequest.getPromotion());
            demandeurFormation.setMention(demaformaRequest.getMention());
            demandeurFormation.setEtablissement(demaformaRequest.getEtablissement());
            demandeurFormation.setDateObtention(demaformaRequest.getDateObtention());
            demandeurFormation.setNomFormation(demaformaRequest.getNomFormation());
            return formationService.saveDemandeurFormation(demandeurFormation);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurformationId" + demandeurformationId + " Not Found "));

    }

}

