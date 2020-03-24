package com.sised.controller;

import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.Demandeur;
import com.sised.service.DemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/SISED")
public class DemandeurController {

    @Autowired
    private DemandeurService demandeurService;

   // @PreAuthorize("hasAuthority('EDIT_PRIVILEGE')")
    @GetMapping("/demandeur")
    public Page<Demandeur> getAllDemandeur(Pageable pageable){
        return demandeurService.getDemandeursPagination(pageable);
    }

//    @GetMapping("/demandeur")
//    public List<Demandeur> getAllDemandeur(){
//        return demandeurService.getDemandeurs();
//    }

    @GetMapping("/demandeur/{id}")
    public ResponseEntity<Demandeur> getDemandeById(@PathVariable(value = "id") Long demandeurId)
            throws ResourceNotFoundException {
        Demandeur demandeur = demandeurService.getDemandeur(demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("echec sur 'get' aucun demandeur trouvé pour ce id " + demandeurId));
        return ResponseEntity.ok().body(demandeur);
    }

//    @PostMapping("/demandeur")
//    public ResponseEntity<Object> createDemandeur(@RequestBody Demandeur demandeur) throws ResourceNotFoundException {
//        Demandeur saveDemandeurs = demandeurService.saveDemandeur(demandeur);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(demandeur.getId()).toUri();
//        return ResponseEntity.created(location).body(getDemandeById(demandeur.getId()));

    @PostMapping("/demandeur")
    public ResponseEntity<Object> createDemandeur(@RequestBody Demandeur demandeur){
        Demandeur saveDemandeurs = demandeurService.saveDemandeur(demandeur);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(demandeur.getId()).toUri();
        return ResponseEntity.created(location).body(saveDemandeurs);

    }

    @PutMapping("/demandeur/{id}")
    public ResponseEntity<Demandeur> updateDemandeur(@PathVariable(value = "id") Long demandeurId, @Valid @RequestBody Demandeur demandeurdetails) throws ResourceNotFoundException {
        Demandeur demandeur = demandeurService.getDemandeur(demandeurId).orElseThrow(() -> new ResourceNotFoundException("echec update ,aucun demandeur trouvé pour ce id  " + demandeurId));

        demandeur.setNom(demandeurdetails.getNom());
        demandeur.setPrenom(demandeurdetails.getPrenom());
        demandeur.setGenre(demandeurdetails.getGenre());
        demandeur.setnationalite(demandeurdetails.getnationalite());
        demandeur.setDateNaissance(demandeurdetails.getDateNaissance());
        demandeur.setLieuNaissance(demandeurdetails.getLieuNaissance());
        demandeur.setAdresse(demandeurdetails.getAdresse());
        demandeur.setTelephone(demandeurdetails.getTelephone());
        demandeur.setEmail(demandeurdetails.getEmail());
        demandeur.setStatus(demandeurdetails.getStatus());
        demandeur.setNumeroPieceDidentite(demandeurdetails.getNumeroPieceDidentite());

        final Demandeur demandeurUpdapted = demandeurService.saveDemandeur(demandeur);
        return ResponseEntity.ok(demandeurUpdapted);
    }

    @DeleteMapping("/demandeur/{id}")
    public Map<String, Boolean> deleteDemandeur(@PathVariable(value = "id") Long demandeurId)
            throws ResourceNotFoundException {
        Demandeur demandeur = demandeurService.getDemandeur(demandeurId).orElseThrow(() -> new ResourceNotFoundException(" echec , Demandeur n'a pas été retrouvé pour ce id  " + demandeurId));
        demandeurService.deleteDemandeur(demandeur);
        Map<String, Boolean> responseobtenu = new HashMap<>();
        responseobtenu.put("Supprimé", Boolean.TRUE);
        return responseobtenu;
    }

}
