package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
 @Table(name = "statusdemande")
 //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
 public class StatusDemande {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id ;
 private String libelle ;

 @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
 private Date date ;
 private String status ;

 @ManyToOne(fetch = FetchType.LAZY, optional = false)
 @JoinColumn(name = "demandeEquivalence_id")
 @JsonIgnoreProperties("statusdemande")
 @JsonIgnore
 private DemandeEquivalence demandeEquivalence;

  public StatusDemande() {
  }

  public StatusDemande(Long id, String libelle, Date date, String status, DemandeEquivalence demandeEquivalence) {
   this.id = id;
   this.libelle = libelle;
   this.date = date;
   this.status = status;
   this.demandeEquivalence=demandeEquivalence;
  }

  public Long getId() {
   return id;
  }

  public void setId(Long id) {
   this.id = id;
  }

  public String getLibelle() {
   return libelle;
  }

  public void setLibelle(String libelle) {
   this.libelle = libelle;
  }

  public Date getDate() {
   return date;
  }

  public void setDate(Date date) {
   this.date = date;
  }

  public String getStatus() {
   return status;
  }

  public void setStatus(String status) {
   this.status = status;
  }

  public DemandeEquivalence getDemandeEquivalence() {
   return demandeEquivalence;
  }

  public void setDemandeEquivalence(DemandeEquivalence demandeEquivalence) {
   this.demandeEquivalence = demandeEquivalence;
  }
 }