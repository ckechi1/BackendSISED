package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DemandeEquivalence")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DemandeEquivalence {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id ;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date dateDepot;
  private Long numeroRecepisse;
  private Long numeroBordereau;
  private String diplomeAnterieur;
  private String diplomeDemande;

  @ManyToOne (fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "demandeur_id")
  @JsonIgnoreProperties("demandeEquivalences")
  @JsonIgnore
  private Demandeur demandeur;

//@OneToOne(mappedBy = "demandeEquivalence")
  @OneToMany(mappedBy = "demandeEquivalence")
  @JsonIgnoreProperties("demandeEquivalence")
  private List<StatusDemande> statusDemande = new ArrayList<>();

  @OneToMany(mappedBy = "demandeEquivalence")
  @JsonIgnoreProperties("demandeEquivalence")
  private List<DocumentFile> documentFile = new ArrayList<>();

    public DemandeEquivalence() {
    }

    public DemandeEquivalence(Long id, Date dateDepot, Long numeroRecepisse, Long numeroBordereau, String diplomeAnterieur, String diplomeDemande , Demandeur demandeur , List<StatusDemande> statusDemande , List<DocumentFile> documentFile) {
        this.id = id;
        this.dateDepot = dateDepot;
        this.numeroRecepisse = numeroRecepisse;
        this.numeroBordereau = numeroBordereau;
        this.diplomeAnterieur = diplomeAnterieur;
        this.diplomeDemande = diplomeDemande;
        this.demandeur=demandeur;
        this.statusDemande=statusDemande;
        this.documentFile=documentFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
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

    public List<DocumentFile> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(List<DocumentFile> documentFile) {
        this.documentFile = documentFile;
    }
}
