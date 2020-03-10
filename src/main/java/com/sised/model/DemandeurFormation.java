package com.sised.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DemandeurFormation")
public class DemandeurFormation { // represente l'association de la classe demandeur et formation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String mention;
    private String promotion;
    private String pays;
    private String etablissement;

   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateObtention;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name  = "formation_id")
   // @JsonIgnoreProperties("DemandeurFormation" )
   // @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Formation formation;

    @ManyToOne (fetch = FetchType.LAZY, optional = false  )
    @JoinColumn(name  = "demandeur_id")
   // @JsonIgnoreProperties("DemandeurFormation")
   // @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private Demandeur demandeur;

    public DemandeurFormation() {

    }

    public DemandeurFormation(Long id, String mention, Date dateObtention ,String pays , String etablissement ,String promotion, Formation formation, Demandeur demandeur) {
        this.id = id;
        this.pays=pays;
        this.mention = mention;
        this.promotion = promotion;
        this.etablissement = etablissement;
        this.dateObtention = dateObtention;
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
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }


}
