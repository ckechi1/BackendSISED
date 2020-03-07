package com.sised.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// in this example i'm using unidirectional way instead of bidirectional
@Entity
@Table(name = "formation")
@JsonPropertyOrder({ "id", "nom", "specialite" , "niveau" , "estDiplomate" })
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String nom;
    private  String specialite;
    private  String niveau ;
    private  String estDiplomate;

    @OneToMany(mappedBy = "formation"  , cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("formation")
    @JsonIgnore
    private List<DemandeurFormation> demandeurFormation = new ArrayList<>();

    public Formation() {

    }

    public Formation(Long id, String nom , String specialite, String estDiplomate , String niveau, List<DemandeurFormation> demandeurFormation) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.demandeurFormation=demandeurFormation;
        this.niveau=niveau;
        this.estDiplomate=estDiplomate;
    }

    public List<DemandeurFormation> getDemandeurFormation() {
        return demandeurFormation;
    }

    public void setDemandeurFormation(List<DemandeurFormation> demandeurFormation) {
        this.demandeurFormation = demandeurFormation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getEstDiplomate() {
        return estDiplomate;
    }

    public void setEstDiplomate(String estDiplomate) {
        this.estDiplomate = estDiplomate;
    }
}
