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
            demandeur1.setDateNaissance("10/10/1980");
            demandeur1.setGenre("masculin");
            demandeur1.setAdresse("Niarela");
            demandeur1.setnationalite("Mali");
            demandeur1.setStatus("stagiare");
            demandeur1.setEmail("abc@gmail.com");
            demandeur1.setTelephone(77552014);
            demandeur1.setNumeroPieceDidentite(1524);
            demandeurRepository.save(demandeur1);

            Formation f3 = new Formation();
            f3.setSpecialite("informatique");
            f3.setNom("france");
            f3.setPays("Master");
            f3.setEtablissement("sup Mali");
            f3.setDateObtention("2 janvier 2058");
            f3.setDemandeur(demandeur1);
            formationRepository.save(f3);

            Formation f2 = new Formation();
            f2.setSpecialite("informatique");
            f2.setNom("france");
            f2.setPays("Master");
            f2.setEtablissement("sup Mali");
            f2.setDateObtention("2 janvier 2058");
            f2.setDemandeur(demandeur1);
            formationRepository.save(f2);

            Formation f4 = new Formation();
            f4.setSpecialite("informatique");
            f4.setNom("france");
            f4.setPays("Master");
            f4.setEtablissement("sup Mali");
            f4.setDateObtention("2 janvier 2058");
            f4.setDemandeur(demandeur1);
            formationRepository.save(f4);


            DemandeEquivalence demandeEqui = new DemandeEquivalence();
            demandeEqui.setDateDepot("3 mars 2006");
            demandeEqui.setNumeroRecepisse((long) 122);
            demandeEqui.setNumeroBordereau((long) 224);
            demandeEqui.setDiplomeAnterieur("BAC +3");
            demandeEqui.setDiplomeDemande("Master");
            demandeEqui.setDemandeur(demandeur1);
            demandeEquivalenceRepo.save(demandeEqui);

            DemandeurFormation demandeurFormation = new DemandeurFormation();
            demandeurFormation.setPromotion("2015");
            demandeurFormation.setMention("Bien");
            demandeurFormation.setEstDiplome(true);
            demandeurFormation.setDemandeur(demandeur1);
            demandeurFormation.setFormation(f2);
            demandeurFormation.setFormation(f3);
            demandeurFormation.setFormation(f4);
            demandeurFormationRepository.save(demandeurFormation);

            StatusDemande statusDemande = new StatusDemande();
            statusDemande.setLibelle("ceci est un status");
            statusDemande.setDate("2 janvier 2015");
            statusDemande.setStatus("en cours de traitement ");
            statusDemande.setDemandeEquivalence(demandeEqui);
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
