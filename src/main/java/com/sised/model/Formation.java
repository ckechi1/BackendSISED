package com.sised.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// in this example i'm using unidirectional way instead of bidirectional
@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String nom;
    private  String pays;
    private  String specialite;
    private  String dateObtention;
    private  String Etablissement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // child of demandeur
    @JoinColumn(name ="demandeur_id", nullable = false)
    @JsonIgnoreProperties("formation")
    @JsonIgnore
    private Demandeur demandeur; // a link to demandeur object

    @OneToMany(mappedBy = "formation")
    @JsonIgnoreProperties("formation")
 //   @JsonIgnore
    private List<DemandeurFormation> demandeurFormation = new ArrayList<>();


    public Formation() {

    }

    public Formation(Long id, String nom, String pays, String specialite, String dateObtention, String etablissement, Demandeur demandeur ,List<DemandeurFormation> demandeurFormation) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.specialite = specialite;
        this.dateObtention = dateObtention;
        this.Etablissement = etablissement;
        this.demandeur = demandeur;
        this.demandeurFormation=demandeurFormation;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(String dateObtention) {
        this.dateObtention = dateObtention;
    }

    public String getEtablissement() {
        return Etablissement;
    }

    public void setEtablissement(String etablissement) {
        Etablissement = etablissement;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }
}
