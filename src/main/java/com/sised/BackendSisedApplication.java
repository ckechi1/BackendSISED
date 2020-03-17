package com.sised;

import com.sised.ExceptionHandling.FileStorageProperties;
import com.sised.model.*;
import com.sised.model.securityModel.Privilege;
import com.sised.model.securityModel.Role;
import com.sised.model.securityModel.user;
import com.sised.repository.*;
import com.sised.securityconfiguration.MyBCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

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
                                      DemandeEquivalenceRepository demandeEquivalenceRepo,
                                      DemandeurFormationRepository demandeurFormationRepository,
                                      StatusDemandeRepository statusDemandeRepository ,
                                      UserRepository userRepository, RoleRepository roleRepository,
                                      PrivilegeRepository privilegeRepository, MyBCryptPasswordEncoder myBCryptPasswordEncoder,
                                      DocumentFileRepository documentFileRepository)
    {
        return args -> {

            Demandeur demandeur1 = new Demandeur();
            demandeur1.setNom("nantomé");
            demandeur1.setPrenom("modi");
            demandeur1.setLieuNaissance("Bamako");
            demandeur1.setDateNaissance(new SimpleDateFormat("dd/MMMM/yyyy" , Locale.FRENCH ).parse("10/janvier/1980"));
            demandeur1.setGenre("masculin");
            demandeur1.setAdresse("Niarela , R 12 P 144");
            demandeur1.setnationalite("Malien");
            demandeur1.setStatus("stagiaire");
            demandeur1.setEmail("modi_nant@gmail.com");
            demandeur1.setTelephone(77552014);
            demandeur1.setNumeroPieceDidentite(1524);
            demandeurRepository.save(demandeur1);

            Demandeur demandeur2 = new Demandeur();
            demandeur2.setNom("Diallo");
            demandeur2.setPrenom("mohamed");
            demandeur2.setLieuNaissance("segou");
            demandeur2.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy" , Locale.FRENCH).parse("10/10/1980"));
            demandeur2.setGenre("masculin");
            demandeur2.setAdresse("ACI");
            demandeur2.setnationalite("nigerien");
            demandeur2.setStatus("fonctionnaire");
            demandeur2.setEmail("avc@gmail.com");
            demandeur2.setTelephone(7754820);
            demandeur2.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur2);

            Demandeur demandeur3 = new Demandeur();
            demandeur3.setNom("zulu");
            demandeur3.setPrenom("Seydou");
            demandeur3.setLieuNaissance("mopti");
            demandeur3.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("12/11/1985"));
            demandeur3.setGenre("masculin");
            demandeur3.setAdresse("zone");
            demandeur3.setnationalite("malien");
            demandeur3.setStatus("stagiaire");
            demandeur3.setEmail("ghd@gmail.com");
            demandeur3.setTelephone(7754820);
            demandeur3.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur3);

            Demandeur demandeur4 = new Demandeur();
            demandeur4.setNom("sanafé");
            demandeur4.setPrenom("fanta");
            demandeur4.setLieuNaissance("Segou");
            demandeur4.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("05/10/1999"));
            demandeur4.setGenre("feminin");
            demandeur4.setAdresse("bagadadji");
            demandeur4.setnationalite("malienne");
            demandeur4.setStatus("fonctionnaire");
            demandeur4.setEmail("vbghn15@gmail.com");
            demandeur4.setTelephone(7754820);
            demandeur4.setNumeroPieceDidentite(15246);
            demandeurRepository.save(demandeur4);

            Formation f1 = new Formation();
            f1.setSpecialite("finance management");
            f1.setNom("Master 1");
            f1.setNiveau("Bac+4");
            f1.setEstDiplomate("OUI");
            formationRepository.save(f1);
            Formation f3 = new Formation();
            f3.setSpecialite("informatique");
            f3.setNom("Master 2");
            f3.setNiveau("Bac+5");
            f3.setEstDiplomate("NON");
            formationRepository.save(f3);
            Formation f4 = new Formation();
            f4.setSpecialite("informatique de gestion ");
            f4.setNom("2UT");
            f4.setNiveau("Bac+2");
            f4.setEstDiplomate("NON");
            formationRepository.save(f4);
            Formation f2 = new Formation();
            f2.setSpecialite("informatique de gestion ");
            f2.setNom("Licence");
            f2.setNiveau("Bac+3");
            f2.setEstDiplomate("OUI");
            formationRepository.save(f2);

            DemandeEquivalence demandeEqui = new DemandeEquivalence();
            demandeEqui.setDateDepot(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("03/09/2006"));
            demandeEqui.setNumeroRecepisse((long) 122);
            demandeEqui.setNumeroBordereau((long) 224);
            demandeEqui.setDiplomeAnterieur("BAC +3");
            demandeEqui.setDiplomeDemande("Master");
            demandeEqui.setDemandeur(demandeur1);

            demandeEquivalenceRepo.save(demandeEqui);

            DemandeEquivalence demandeEqui2 = new DemandeEquivalence();
            demandeEqui2.setDateDepot(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("12/10/2015"));
            demandeEqui2.setNumeroRecepisse((long) 122);
            demandeEqui2.setNumeroBordereau((long) 224);
            demandeEqui2.setDiplomeAnterieur("Master");
            demandeEqui2.setDiplomeDemande("Doctorat");
            demandeEqui2.setDemandeur(demandeur1);
            demandeEquivalenceRepo.save(demandeEqui2);

            DemandeurFormation demandeurFormation = new DemandeurFormation();
            demandeurFormation.setPromotion("2015");
            demandeurFormation.setMention("Bien");
            demandeurFormation.setPays("France");
            demandeurFormation.setEtablissement("La Corniche");
            demandeurFormation.setDateObtention(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("10/10/2015"));
            demandeurFormation.setFormation(f1);
            demandeurFormation.setFormation(f2);
            demandeurFormation.setDemandeur(demandeur1);
            demandeurFormationRepository.save(demandeurFormation);

            DemandeurFormation demandeurFormation2 = new DemandeurFormation();
            demandeurFormation2.setPromotion("2015");
            demandeurFormation2.setMention("Bien");
            demandeurFormation2.setPays("Mali");
            demandeurFormation2.setEtablissement("Sup Management");
            demandeurFormation2.setDateObtention(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("02/10/2015"));
            demandeurFormation2.setFormation(f3);
            demandeurFormation2.setFormation(f4);
            demandeurFormation2.setDemandeur(demandeur2);
            demandeurFormationRepository.save(demandeurFormation2);

            StatusDemande statusDemande = new StatusDemande();
            statusDemande.setLibelle("ceci est un status");
            statusDemande.setDate(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse("02/10/2015"));
            statusDemande.setStatus("en cours de traitement");
            statusDemande.setDemandeEquivalence(demandeEqui);
            statusDemandeRepository.save(statusDemande);

            DocumentFile docfile = new DocumentFile();
            docfile.setNomFichier("diplome de master");
            docfile.setCheminFichier("locahost:8080/upload/diplome.png");
            docfile.setTypeFichier("image/png");
            docfile.setTailleFichier((long) 25586);
            docfile.setDemandeEquivalence(demandeEqui);
            documentFileRepository.save(docfile);

            roleRepository.deleteAll();
            privilegeRepository.deleteAll();
            userRepository.deleteAll();

            Privilege EDIT_PRIVILEGE = privilegeRepository.save(new Privilege("EDIT_PRIVILEGE"));
            Privilege READ_PRIVILEGE = privilegeRepository.save(new Privilege("READ_PRIVILEGE"));
            Privilege DELETE_PRIVILEGE = privilegeRepository.save(new Privilege("DELETE_PRIVILEGE"));

            Role ROLE_ADMIN = roleRepository.save(new Role("ROLE_ADMIN",
                                                  Arrays.asList(EDIT_PRIVILEGE, READ_PRIVILEGE, DELETE_PRIVILEGE)));

            Role ROLE_USER = roleRepository.save(new Role("ROLE_USER", Arrays.asList(READ_PRIVILEGE)));

            userRepository.save(new user("admin", myBCryptPasswordEncoder.encode("admin"),
                               Arrays.asList(ROLE_ADMIN, ROLE_USER)));
            userRepository.save(new user("user", myBCryptPasswordEncoder.encode("user"),
                               Arrays.asList(ROLE_USER)));
        };
    }
}
