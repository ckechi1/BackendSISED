package com.sised.model;


import javax.persistence.*;

@Entity
@Table(name = "Demandeur")
public class Demandeur {

        private Long  id;
        private String nom;
        private String prenom;
        private String genre;
        private String nationalite;
        private String dateNaissance;
        private String lieuNaissance;
        private String adresse;
        private int telephone;
        private String email;
        private String status;
        private int numeroPieceDidentite;

    public Demandeur(Long  id, String nom, String prenom, String genre, String nationalite, String dateNaissance, String lieuNaissance, String adresse, int telephone, String email, String status, int numeroPieceDidentite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.nationalite = nationalite;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
        this.numeroPieceDidentite = numeroPieceDidentite;
    }

    public Demandeur() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }
    @Column(name = "Nom", nullable = true)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @Column(name = "Prenom", nullable = true)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "Genre", nullable = true)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Column(name = "nationalite", nullable = true)
    public String getnationalite() {
        return nationalite;
    }

    public void setnationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Column(name = "Naissance", nullable = true)
    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Column(name = "Lieu_Naissance", nullable = true)
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Column(name = "Adresse", nullable = true)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Column(name = "Telephone", nullable = true)
    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Column(name = "Email", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Status", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "Numero_Piece_didentite", nullable = true)
    public int getNumeroPieceDidentite() {
        return numeroPieceDidentite;
    }

    public void setNumeroPieceDidentite(int numeroPieceDidentite) {
        this.numeroPieceDidentite = numeroPieceDidentite;
    }


}
