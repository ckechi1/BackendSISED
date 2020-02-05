package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DemandeEquivalence")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DemandeEquivalence {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id ;
  private String dateDepot;
  private Long numeroRecepisse;
  private Long numeroBordereau;
  private String diplomeAnterieur;
  private String diplomeDemande;

  @ManyToOne (fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "demandeur_id")
  @JsonIgnoreProperties("demandeEquivalences")
  @JsonIgnore
  private Demandeur demandeur;

  @OneToMany(mappedBy = "demandeEquivalence")
  @JsonIgnoreProperties("demandeEquivalence")
  //@OneToOne(mappedBy = "demandeEquivalence")
  private List<StatusDemande> statusDemande = new ArrayList<>();

    public DemandeEquivalence() {
    }

    public DemandeEquivalence(Long id, String dateDepot, Long numeroRecepisse, Long numeroBordereau, String diplomeAnterieur, String diplomeDemande , Demandeur demandeur , List<StatusDemande> statusDemande) {
        this.id = id;
        this.dateDepot = dateDepot;
        this.numeroRecepisse = numeroRecepisse;
        this.numeroBordereau = numeroBordereau;
        this.diplomeAnterieur = diplomeAnterieur;
        this.diplomeDemande = diplomeDemande;
        this.demandeur=demandeur;
        this.statusDemande=statusDemande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(String dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Long getNumeroRecepisse() {
        return numeroRecepisse;
    }

    public void setNumeroRecepisse(Long numeroRecepisse) {
        this.numeroRecepisse = numeroRecepisse;
    }

    public Long getNumeroBordereau() {
        return numeroBordereau;
    }

    public void setNumeroBordereau(Long numeroBordereau) {
        this.numeroBordereau = numeroBordereau;
    }

    public String getDiplomeAnterieur() {
        return diplomeAnterieur;
    }

    public void setDiplomeAnterieur(String diplomeAnterieur) {
        this.diplomeAnterieur = diplomeAnterieur;
    }

    public String getDiplomeDemande() {
        return diplomeDemande;
    }

    public void setDiplomeDemande(String diplomeDemande) {
        this.diplomeDemande = diplomeDemande;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public List<StatusDemande> getStatusDemande() {
        return statusDemande;
    }

    public void setStatusDemande(List<StatusDemande> statusDemande) {
        this.statusDemande = statusDemande;
    }
}
