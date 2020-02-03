package com.sised.model;

import javax.persistence.*;

 @Entity
 @Table(name = "statusdemande")
 public class StatusDemande {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id ;
 private String libelle ;
 private String date ;
 private String status ;

}