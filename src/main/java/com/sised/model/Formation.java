package com.sised.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name ="demandeur_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Demandeur demandeur;

    public Formation() {

    }

    public Formation(Long id, String nom, String pays, String specialite, String dateObtention, String etablissement, Demandeur demandeur) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.specialite = specialite;
        this.dateObtention = dateObtention;
        this.Etablissement = etablissement;
        this.demandeur = demandeur;
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
