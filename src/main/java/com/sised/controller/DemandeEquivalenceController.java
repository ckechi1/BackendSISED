package com.sised.controller;
import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.DemandeEquivalence;
import com.sised.model.StatusDemande;
import com.sised.service.DemandeEquiService;
import com.sised.service.DemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/SISED/demandeur/{demandeurId}")
public class DemandeEquivalenceController {

    @Autowired
    private DemandeurService demandeurService;

    @Autowired
    private DemandeEquiService demandeEquiService;


    @GetMapping("/DemandeEquivalence")
    public Page<DemandeEquivalence> getAllDemandeEquivalences(@PathVariable(value = "demandeurId") Long demandeurId, Pageable pageable) {
        return demandeEquiService.getDemandeEquivalences(demandeurId , pageable);
    }

    @PostMapping("/DemandeEquivalence")
    public DemandeEquivalence CreateDemandeEquivalenceById(@PathVariable(value = "demandeurId") Long demandeurId,
                                                           @Valid @RequestBody DemandeEquivalence demandeEquivalence)
            throws ResourceNotFoundException {
        return demandeurService.getDemandeur(demandeurId).map(demandeur -> {
            demandeEquivalence.setDemandeur(demandeur);
            return demandeEquiService.saveDemandeEquivalence(demandeEquivalence);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurId " + demandeurId + "Not found"));
    }

    @PutMapping("/DemandeEquivalence/{demandeEquiId}")
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
          //DemandeEquivalence.setDemandeur(demandeEquivalenceRequest.getDemandeur());
            return demandeEquiService.saveDemandeEquivalence(DemandeEquivalence);
        }).orElseThrow(() -> new ResourceNotFoundException("demandeEquivalenceID " + demandeEquiId + "not found"));

    }

    @DeleteMapping("/DemandeEquivalence/{demandeEquiId}")
    public ResponseEntity<?> deleteDemandeEquivalence(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                      @PathVariable(value = "demandeurId") Long demandeurId)
            throws ResourceNotFoundException {
        return demandeEquiService.getDemandeEquivalenceByIdFormationId(demandeEquiId, demandeurId).map(formation -> {
            demandeEquiService.deleteDemandeEquivalenceById(demandeEquiId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("demandeEquivalence not found with id " + demandeEquiId + "demandeur" + demandeurId + "demandeurId"));

    }

    @GetMapping("/DemandeEquivalence/{demandeEquiId}")
    public ResponseEntity<DemandeEquivalence> getdemandeEquiByIdDemandeurById(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                                              @PathVariable(value = "demandeurId") Long demandeurId)
            throws ResourceNotFoundException {
        DemandeEquivalence demandeEquivalence = demandeEquiService.getDemandeEquivalenceByIdFormationId(demandeEquiId, demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("demandeEquivalence not found with Id " + demandeEquiId + " and demandeur Id  " + demandeurId));
        return ResponseEntity.ok().body(demandeEquivalence);
    }

    //////////////////////////// statusDemande controller /////////////////////////
    @GetMapping("/DemandeEquivalence/{demandeEquiId}/statusdemande")
    public List<StatusDemande> getStatusdemandes() {
        return demandeEquiService.getStatusDemandes();
    }

    @GetMapping("/DemandeEquivalence/{demandeEquiId}/statusdemande/{statusdemandeId}")
    public ResponseEntity<StatusDemande> getStatusDemandebyId(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                              @PathVariable(value = "statusdemandeId") Long statusdemandeId)
            throws ResourceNotFoundException {
        StatusDemande statusdemande = demandeEquiService.getDemandeEquivalenceAndStatusById(statusdemandeId, demandeEquiId)
                .orElseThrow(() -> new ResourceNotFoundException("statusdemandeId " + statusdemandeId + "Not found with demandeEquiId " + demandeEquiId));
        return ResponseEntity.ok().body(statusdemande);
    }

    @PostMapping("/DemandeEquivalence/{demandeEquiId}/statusdemande")
    public StatusDemande createStatusDemande(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                             @PathVariable(value = "demandeurId") Long demandeurId,
                                             @Valid @RequestBody StatusDemande statusDemandeRequest)
            throws ResourceNotFoundException {
        if (!demandeurService.getDemanderbyIdIfExists(demandeurId)) {
            throw new ResourceNotFoundException("Le demandeur avec L'identifiant " + demandeurId + "nexite pas");
        } else
            return demandeEquiService.getDemandeEquivalence(demandeEquiId).map(demandeEquivalence -> {
                statusDemandeRequest.setDemandeEquivalence(demandeEquivalence);
                return demandeEquiService.saveStatusdemande(statusDemandeRequest);
            }).orElseThrow(() -> new ResourceNotFoundException("demandeEquivalenceId" + demandeEquiId + "Not found"));

    }

    @DeleteMapping("/DemandeEquivalence/{demandeEquiId}/statusdemande/{statusdemandeId}")
    public ResponseEntity<?> deleteStatusDemande(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                                 @PathVariable(value = "statusdemandeId") Long statusdemandeId)
                                                 throws ResourceNotFoundException {
        return demandeEquiService.getDemandeEquivalenceAndStatusById(statusdemandeId, demandeEquiId).map(statusdemande -> {
            demandeEquiService.deleteStatusDemande(statusdemandeId);
            return ResponseEntity.ok().body(getStatusdemandes());
        }).orElseThrow(() -> new ResourceNotFoundException("demandeurEquivalence Id " + demandeEquiId + " Not found and " + "statusdemande Id " + statusdemandeId));
    }

    @PutMapping("/DemandeEquivalence/{demandeEquiId}/statusdemande/{statusdemandeId}")
    public StatusDemande updateStatusdemande(@PathVariable(value = "demandeEquiId") Long demandeEquiId,
                                             // @PathVariable(value = "demandeurId") Long demandeurId,
                                             @PathVariable(value = "statusdemandeId") Long statusdemandeId,
                                             @Valid @RequestBody StatusDemande statusDemandeRequest)
            throws ResourceNotFoundException {
        if (!demandeEquiService.getDemandeEquivalenceByIdIfExists(demandeEquiId)) {
            throw new ResourceNotFoundException("demande equivalence id " + demandeEquiId + "Not Found ");
        }
            return demandeEquiService.getStatusdemandeById(statusdemandeId).map(statusDemande -> {
            statusDemande.setLibelle(statusDemandeRequest.getLibelle());
            statusDemande.setStatus(statusDemandeRequest.getStatus());
            statusDemande.setDate(statusDemandeRequest.getDate());
            return demandeEquiService.saveStatusdemande(statusDemande);

        }).orElseThrow(() -> new ResourceNotFoundException("demandeurEquivalence Id " + demandeEquiId + " Not found and " + "statusdemande Id " + statusdemandeId));

    }
}