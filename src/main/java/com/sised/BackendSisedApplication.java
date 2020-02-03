package com.sised;

import com.sised.model.DemandeEquivalence;
import com.sised.model.Demandeur;
import com.sised.model.DemandeurFormation;
import com.sised.model.Formation;
import com.sised.repository.DemandeEquivalenceRepository;
import com.sised.repository.DemandeurFormationRepository;
import com.sised.repository.FormationRepository;
import jdk.nashorn.internal.objects.NativeArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sised.repository.DemandeurRepository;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@EnableAutoConfiguration
@SpringBootApplication
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
                                      DemandeEquivalenceRepository demandeEquivalenceRepo, DemandeurFormationRepository demandeurFormationRepository) {
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

            Formation f1 = new Formation();
            f1.setSpecialite("informatique");
            f1.setNom("france");
            f1.setPays("Master");
            f1.setEtablissement("sup Mali");
            f1.setDateObtention("2 janvier 2058");
            f1.setDemandeur(demandeur1);
            formationRepository.save(f1);

            DemandeEquivalence demandeEqui = new DemandeEquivalence();
            demandeEqui.setDateDepot("3 mars 2060");
            demandeEqui.setNumeroRecepisse((long) 122);
            demandeEqui.setNumeroBordereau((long) 224);
            demandeEqui.setDiplomeAnterieur("BAC +3");
            demandeEqui.setDiplomeDemande("Master");
            demandeEqui.setDemandeur(demandeur1);
            demandeEquivalenceRepo.save(demandeEqui);

            DemandeurFormation demandeurFormation = new DemandeurFormation();
            demandeurFormation.setNiveau("15");
            demandeurFormation.setMention("Bien");
            demandeurFormation.setEstDiplomé(true);
            demandeurFormation.setDemandeur(demandeur1);
            demandeurFormation.setFormation(f1);
            demandeurFormationRepository.save(demandeurFormation);


        };
    }
}
