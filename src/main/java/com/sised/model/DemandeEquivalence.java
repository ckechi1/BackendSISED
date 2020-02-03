package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "DemandeEquivalence")
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

    public DemandeEquivalence() {
    }

    public DemandeEquivalence(Long id, String dateDepot, Long numeroRecepisse, Long numeroBordereau, String diplomeAnterieur, String diplomeDemande , Demandeur demandeur) {
        this.id = id;
        this.dateDepot = dateDepot;
        this.numeroRecepisse = numeroRecepisse;
        this.numeroBordereau = numeroBordereau;
        this.diplomeAnterieur = diplomeAnterieur;
        this.diplomeDemande = diplomeDemande;
        this.demandeur=demandeur;
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

}
