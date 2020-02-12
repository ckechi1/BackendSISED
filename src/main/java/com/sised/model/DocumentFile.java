package com.sised.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "DocumentFile")
public class DocumentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomFichier;
    private String cheminFichier;
    private String typeFichier;
    private Long tailleFichier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // child of demandeEquivalence
    @JoinColumn(name ="demandeEquivalence_id", nullable = false)
    @JsonIgnoreProperties("DocumentFile")
    @JsonIgnore
    private DemandeEquivalence demandeEquivalence;

    public DocumentFile(Long id, String nomFichier, String cheminFichier, String typeFichier, Long tailleFichier , DemandeEquivalence demandeEquivalence) {
        this.id = id;
        this.nomFichier = nomFichier;
        this.cheminFichier = cheminFichier;
        this.typeFichier = typeFichier;
        this.tailleFichier = tailleFichier;
        this.demandeEquivalence=demandeEquivalence;
    }

    public DocumentFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(String typeFichier) {
        this.typeFichier = typeFichier;
    }

    public Long getTailleFichier() {
        return tailleFichier;
    }

    public void setTailleFichier(Long tailleFichier) {
        this.tailleFichier = tailleFichier;
    }

    public DemandeEquivalence getDemandeEquivalence() {
        return demandeEquivalence;
    }

    public void setDemandeEquivalence(DemandeEquivalence demandeEquivalence) {
        this.demandeEquivalence = demandeEquivalence;
    }
}
