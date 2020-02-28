package com.sised;

import com.sised.ExceptionHandling.FileStorageProperties;
import com.sised.model.*;
import com.sised.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class BackendSisedApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSisedApplication.class, args);
    }

//	@Bean
//	CommandLineRunner init(DemandeurRepository repository ) {
//		return args -> {
//			repository.deleteAll();
//			LongStream.range(1, 7)
//					.mapToObj(i -> {
//						Demandeur d = new Demandeur();
//						d.setPrenom("test" + i);
//						d.setAdresse("newTown" + i);
//						d.setNom("Nom " + i);
//						d.setEmail("contact" + i + "@email.com");
//						d.setTelephone(77523645);
//						d.setNumeroPieceDidentite(458288);
//						d.setStatus("single" + i);
//						d.setGenre("Masculin" + i);
//						d.setnationalite("malien" + i);
//						d.setDateNaissance("01/03/09" + i);
//						d.setLieuNaissance("nowhere" + i);
//						return d;
//					})
//					.map(v -> repository.save(v))
//					.forEach(System.out::println);
//		};
//	}

    @Bean
    public CommandLineRunner TestDemo(DemandeurRepository demandeurRepository, FormationRepository formationRepository,
                                      DemandeEquivalenceRepository demandeEquivalenceRepo, DemandeurFormationRepository demandeurFormationRepository,
                                      StatusDemandeRepository statusDemandeRepository , DocumentFileRepository documentFileRepository)
    {
        return args -> {

            Demandeur demandeur1 = new Demandeur();
            demandeur1.setNom("nantomé");
            demandeur1.setPrenom("modi");
            demandeur1.setLieuNaissance("Bamako");
            demandeur1.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1980"));
            demandeur1.setGenre("masculin");
            demandeur1.setAdresse("Niarela");
            demandeur1.setnationalite("Mali");
            demandeur1.setStatus("stagiare");
            demandeur1.setEmail("abc@gmail.com");
            demandeur1.setTelephone(77552014);
            demandeur1.setNumeroPieceDidentite(1524);
            demandeurRepository.save(demandeur1);

            Demandeur demandeur2 = new Demandeur();
            demandeur2.setNom("Diallo");
            demandeur2.setPrenom("mohamed");
            demandeur2.setLieuNaissance("segou");
            demandeur2.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1980"));
            demandeur2.setGenre("masculin");
            demandeur2.setAdresse("ACI");
            demandeur2.setnationalite("nigerien");
            demandeur2.setStatus("stagiare");
            demandeur2.setEmail("avc@gmail.com");
            demandeur2.setTelephone(7754820);
            demandeur2.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur2);

            Demandeur demandeur3 = new Demandeur();
            demandeur3.setNom("zulu");
            demandeur3.setPrenom("Seydou");
            demandeur3.setLieuNaissance("mopti");
            demandeur3.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("12/11/1985"));
            demandeur3.setGenre("masculin");
            demandeur3.setAdresse("zone");
            demandeur3.setnationalite("malien");
            demandeur3.setStatus("stagiare");
            demandeur3.setEmail("ghd@gmail.com");
            demandeur3.setTelephone(7754820);
            demandeur3.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur3);

            Demandeur demandeur4 = new Demandeur();
            demandeur4.setNom("sanafé");
            demandeur4.setPrenom("fanta");
            demandeur4.setLieuNaissance("Segou");
            demandeur4.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("05/10/1999"));
            demandeur4.setGenre("feminin");
            demandeur4.setAdresse("bagadadji");
            demandeur4.setnationalite("malienne");
            demandeur4.setStatus("stagiare");
            demandeur4.setEmail("vbghn15@gmail.com");
            demandeur4.setTelephone(7754820);
            demandeur4.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur4);

            Formation f1 = new Formation();
            f1.setSpecialite("finance management ");
            f1.setNom("Mali");
            f1.setPays("2UT");
            f1.setetablissement("HETECH");
            f1.setDateObtention(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2011"));
            f1.setDemandeur(demandeur1);
            formationRepository.save(f1);

            Formation f3 = new Formation();
            f3.setSpecialite("informatique");
            f3.setNom("france");
            f3.setPays("Master");
            f3.setetablissement("Sorbone");
            f3.setDateObtention(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2013"));
            f3.setDemandeur(demandeur1);
            formationRepository.save(f3);

            Formation f2 = new Formation();
            f2.setSpecialite("informatique de gestion ");
            f2.setNom("Mali");
            f2.setPays("Licence");
            f2.setetablissement("HETECH");
            f2.setDateObtention(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020"));
            f2.setDemandeur(demandeur2);
            formationRepository.save(f2);

            DemandeEquivalence demandeEqui = new DemandeEquivalence();
            demandeEqui.setDateDepot(new SimpleDateFormat("dd/MM/yyyy").parse("03/09/2006"));
            demandeEqui.setNumeroRecepisse((long) 122);
            demandeEqui.setNumeroBordereau((long) 224);
            demandeEqui.setDiplomeAnterieur("BAC +3");
            demandeEqui.setDiplomeDemande("Master");
            demandeEqui.setDemandeur(demandeur1);
            demandeEquivalenceRepo.save(demandeEqui);

            DemandeEquivalence demandeEqui2 = new DemandeEquivalence();
            demandeEqui2.setDateDepot(new SimpleDateFormat("dd/MM/yyyy").parse("15/10/2015"));
            demandeEqui2.setNumeroRecepisse((long) 122);
            demandeEqui2.setNumeroBordereau((long) 224);
            demandeEqui2.setDiplomeAnterieur("Master");
            demandeEqui2.setDiplomeDemande("Doctorat");
            demandeEqui2.setDemandeur(demandeur1);
            demandeEquivalenceRepo.save(demandeEqui2);

            DemandeurFormation demandeurFormation = new DemandeurFormation();
            demandeurFormation.setPromotion("2015");
            demandeurFormation.setMention("Bien");
            demandeurFormation.setEstDiplome(true);
            demandeurFormation.setDemandeur(demandeur1);
            demandeurFormation.setDemandeur(demandeur2);
            demandeurFormation.setFormation(f1);
            demandeurFormation.setFormation(f2);
            demandeurFormation.setFormation(f3);
            demandeurFormationRepository.save(demandeurFormation);

            StatusDemande statusDemande = new StatusDemande();
            statusDemande.setLibelle("ceci est un status");
            statusDemande.setDate(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2015"));
            statusDemande.setStatus("en cours de traitement");
            statusDemande.setDemandeEquivalence(demandeEqui);
            statusDemande.setDemandeEquivalence(demandeEqui2);
            statusDemandeRepository.save(statusDemande);

            DocumentFile docfile = new DocumentFile();
            docfile.setNomFichier("diplome de master");
            docfile.setCheminFichier("locahost:8080/upload/diplome.png");
            docfile.setTypeFichier("image/png");
            docfile.setTailleFichier((long) 25586);
            docfile.setDemandeEquivalence(demandeEqui);
            documentFileRepository.save(docfile);

        };
    }
}
