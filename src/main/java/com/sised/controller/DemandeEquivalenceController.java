package com.sised.controller;
import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.DemandeEquivalence;
import com.sised.service.DemandeEquiService;
import com.sised.service.DemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/SISED/demandeur")
public class DemandeEquivalenceController {

    @Autowired
    private DemandeurService demandeurService;

    @Autowired
    private DemandeEquiService demandeEquiService;

    @GetMapping("/{demandeurId}/DemandeEquivalence")
    public List<DemandeEquivalence> getAllDemandeEquivalences() {
        return demandeEquiService.getDemandeEquivalences();
    }

    @PostMapping("/{demandeurId}/DemandeEquivalence")
    public DemandeEquivalence CreateDemandeEquivalenceById(@PathVariable(value = "demandeurId") Long demandeurId,
                                                              @Valid @RequestBody DemandeEquivalence demandeEquivalence)
                                                                 throws ResourceNotFoundException {
        return demandeurService.getDemandeur(demandeurId).map(demandeur -> {
            demandeEquivalence.setDemandeur(demandeur);
            return demandeEquiService.saveDemandeEquivalence(demandeEquivalence);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurId " + demandeurId + "Not found"));
    }

    @PutMapping("/{demandeurId}/DemandeEquivalence/{demandeEquiId}")
    public DemandeEquivalence updateDemandeEqui(@PathVariable(value = "demandeurId") Long demandeurId,
                                                @PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                @Valid @RequestBody DemandeEquivalence demandeEquivalenceRequest) throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {
            throw new ResourceNotFoundException("demandeur ID " + demandeurId + "not found ");

        } else return demandeEquiService.getDemandeEquivalence(demandeEquiId).map(DemandeEquivalence -> {
            DemandeEquivalence.setDateDepot(demandeEquivalenceRequest.getDateDepot());
            DemandeEquivalence.setDiplomeAnterieur(demandeEquivalenceRequest.getDiplomeAnterieur());
            DemandeEquivalence.setNumeroRecepisse(demandeEquivalenceRequest.getNumeroRecepisse());
            DemandeEquivalence.setNumeroBordereau(demandeEquivalenceRequest.getNumeroBordereau());
            DemandeEquivalence.setDiplomeDemande(demandeEquivalenceRequest.getDiplomeDemande());
            DemandeEquivalence.setDemandeur(demandeEquivalenceRequest.getDemandeur());
            return demandeEquiService.saveDemandeEquivalence(DemandeEquivalence);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeEquivalenceID " + demandeEquiId + "not found"));

    }

    @DeleteMapping("/{demandeurId}/DemandeEquivalence/{demandeEquiId}")
    public ResponseEntity<?> deleteDemandeEquivalence(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                @PathVariable(value = "demandeurId") Long demandeurId)
                                                     throws ResourceNotFoundException {
        return demandeEquiService.getDemandeEquivalenceByIdFormationId(demandeEquiId, demandeurId).map(formation -> {
            demandeEquiService.deleteDemandeEquivalenceById(demandeEquiId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("demandeEquivalence not found with id " + demandeEquiId + "demandeur" + demandeurId + "demandeurId"));

    }
    @GetMapping("/{demandeurId}/DemandeEquivalence/{demandeEquiId}")
    public ResponseEntity<DemandeEquivalence> getdemandeEquiByIdDemandeurById(@PathVariable(value = "demandeEquiId") Long demandeEquiId ,
                                                                               @PathVariable(value = "demandeurId") Long demandeurId)
                                                                                    throws ResourceNotFoundException {
        DemandeEquivalence demandeEquivalence = demandeEquiService.getDemandeEquivalenceByIdFormationId(demandeEquiId, demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("demandeEquivalence not found with Id " + demandeEquiId + " and demandeur Id  " + demandeurId));
        return ResponseEntity.ok().body(demandeEquivalence);
    }

}