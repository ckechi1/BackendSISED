package com.sised.controller;

import com.sised.ExceptionHandling.ResourceNotFoundException;
import com.sised.model.Demandeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sised.repository.DemandeurRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/SISED")
public class DemandeurController {

    @Autowired
    private DemandeurRepository demandeurRepository;

    @GetMapping("/demandeur")
    public List<Demandeur> getAllDemandeur(){
        return demandeurRepository.findAll();
    }

    @GetMapping("/demandeur/{id}")
    public ResponseEntity<Demandeur> getDemandeById(@PathVariable(value = "id") Long demandeurId)
            throws ResourceNotFoundException {
        Demandeur demandeur = demandeurRepository.findById(demandeurId)
                .orElseThrow(() -> new ResourceNotFoundException("echec get aucun demandeur trouvé pour ce id " + demandeurId));
        return ResponseEntity.ok().body(demandeur);
    }

     /*@PostMapping("/demandeur" )
      public Demandeur createDemandeur(@Valid @RequestBody Demandeur demandeur) {
             return demandeurRepository.save(demandeur);
     } */

    @PostMapping("/demandeur")
    public ResponseEntity<Object> createDemandeur(@RequestBody Demandeur demandeur) {
        Demandeur saveDemandeurs = demandeurRepository.save(demandeur);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(demandeur.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/demandeur/{id}")
    public ResponseEntity<Demandeur> updateDemandeur(@PathVariable(value = "id") Long demandeurId, @Valid @RequestBody Demandeur demandeurdetails) throws ResourceNotFoundException {
        Demandeur demandeur = demandeurRepository.findById(demandeurId).orElseThrow(() -> new ResourceNotFoundException("echec update ,aucun demandeur trouvé pour ce id  " + demandeurId));

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

        final Demandeur demandeurUpdapted = demandeurRepository.save(demandeur);
        return ResponseEntity.ok(demandeurUpdapted);
    }

    @DeleteMapping("/demandeur/{id}")
    public Map<String, Boolean> deleteDemandeur(@PathVariable(value = "id") Long demandeurId)
            throws ResourceNotFoundException {
        Demandeur demandeur = demandeurRepository.findById(demandeurId).orElseThrow(() -> new ResourceNotFoundException(" echec , Demandeur n'a pas été retrouvé pour ce id  " + demandeurId));

        demandeurRepository.delete(demandeur);
        Map<String, Boolean> responseobtenu = new HashMap<>();
        responseobtenu.put("Supprimé", Boolean.TRUE);
        return responseobtenu;
    }

}
