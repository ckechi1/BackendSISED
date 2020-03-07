package com.sised.controller;
import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.Formation;
import com.sised.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SISED")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/formations")
    public List<Formation> gellAllFormation() {
        return formationService.getAllFormation();
    }

    @GetMapping("/formation")
    public Page<Formation> getFormationPaginated(Pageable pageable) {
        return formationService.getFormationsPagination(pageable);
    }

    @PostMapping("/formation")
    public ResponseEntity<Formation> createFormation(@Valid @RequestBody Formation formation) {
        Formation saveFormation = formationService.saveFormation(formation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(formation.getId()).toUri();
        return ResponseEntity.created(location).body(saveFormation);
    }

    @PutMapping("/formation/{formationId}")
    public Formation updateFormation(@PathVariable(value = "formationId") Long formationId,
                                     @Valid @RequestBody Formation formationRequest)
                                     throws ResourceNotFoundException {
        return formationService.getFormation(formationId).map(formation -> {
            formation.setNom(formationRequest.getNom());
            formation.setEstDiplomate(formationRequest.getEstDiplomate());
            formation.setSpecialite(formationRequest.getSpecialite());
            formation.setNiveau(formationRequest.getNiveau());
            return formationService.saveFormation(formation);
        }).orElseThrow(() -> new ResourceNotFoundException(" formationId " + formationId + "Not Found "));
    }

    @DeleteMapping("/formation/{formationId}")
    public Map<String, Boolean> deleteFormation(@PathVariable(value = "formationId") Long formationId)
            throws ResourceNotFoundException {
        Formation formation = formationService.getFormation(formationId)
                .orElseThrow(() -> new ResourceNotFoundException(" echec aucune formation pour  " + formationId));
        formationService.deleteFormationById(formationId);
        Map<String, Boolean> responseobtenu = new HashMap<>();
        responseobtenu.put("Supprimé", Boolean.TRUE);
        return responseobtenu;
    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<Formation> getFormationId(@PathVariable(value = "formationId") Long formationId)
                                                    throws ResourceNotFoundException {
        Formation formation = formationService.getFormation(formationId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found with Id " + formationId ));
        return ResponseEntity.ok().body(formation);
    }
}
