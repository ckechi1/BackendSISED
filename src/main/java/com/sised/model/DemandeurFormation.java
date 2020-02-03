package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "DemandeurFormation")
public class DemandeurFormation { // represente l'association de la classe demandeur et formation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String Mention ;
    private String Niveau ;
    private Boolean estDiplomé;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "demandeur_id")
    @JsonIgnoreProperties("DemandeurFormation")
    @JsonIgnore
    private Formation formation;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("DemandeurFormation")
    @JsonIgnore
    private Demandeur demandeur;

    public DemandeurFormation() {
    }

    public DemandeurFormation(Long id, String mention, String niveau, Boolean estDiplomé, Formation formation, Demandeur demandeur) {
        this.id = id;
        Mention = mention;
        Niveau = niveau;
        this.estDiplomé = estDiplomé;
        this.formation = formation;
        this.demandeur = demandeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMention() {
        return Mention;
    }

    public void setMention(String mention) {
        Mention = mention;
    }

    public String getNiveau() {
        return Niveau;
    }

    public void setNiveau(String niveau) {
        Niveau = niveau;
    }

    public Boolean getEstDiplomé() {
        return estDiplomé;
    }

    public void setEstDiplomé(Boolean estDiplomé) {
        this.estDiplomé = estDiplomé;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }
}
