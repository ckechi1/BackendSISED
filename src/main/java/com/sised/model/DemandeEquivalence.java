package com.sised.model;

public class DemandeEquivalence {
  private int id ;
  private String dateDepot;
  private int numeroRecepisse;
  private int numeroBordereau;
  private String diplomeAnterieur;
  private String diplomeDemande;

    public DemandeEquivalence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(String dateDepot) {
        this.dateDepot = dateDepot;
    }

    public int getNumeroRecepisse() {
        return numeroRecepisse;
    }

    public void setNumeroRecepisse(int numeroRecepisse) {
        this.numeroRecepisse = numeroRecepisse;
    }

    public int getNumeroBordereau() {
        return numeroBordereau;
    }

    public void setNumeroBordereau(int numeroBordereau) {
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
}
