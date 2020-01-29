package com.sised;

import com.sised.model.Demandeur;
import com.sised.model.Formation;
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
   public CommandLineRunner TestDemo(DemandeurRepository demandeurRepository , FormationRepository formationRepository){
		return args -> {
			 Demandeur demandeur1 = new Demandeur();
			demandeur1.setNom("modi");
			demandeur1.setPrenom("nantomé");

			 demandeurRepository.save(demandeur1);

			 Formation f1 = new Formation();
			 f1.setNom("france");
			 f1.setNom("Master");
			 f1.setDemandeur(demandeur1);
			 formationRepository.save(f1);

		};
   }
}
