package TP.SI.tpIntegration;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import TP.SI.tpIntegration.entities.Client;
import TP.SI.tpIntegration.entities.Compte;
import TP.SI.tpIntegration.repositories.ClientRepository;
import TP.SI.tpIntegration.repositories.CompteRepository;

@SpringBootApplication
public class TpIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpIntegrationApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository, CompteRepository acountRepository) {
		return args -> {
			Client client = clientRepository
					.save(new Client(1, "BOUILI", "Edouard", "2004-3-3", "Masculin", "Agoe2lion",
							"+228 91619735", "bouiliedourd@gmail.com", "Libannaise", null));
			acountRepository
					.save(new Compte(1, "adtd", "Epargne", "2004-3-3", 780000.0, client));
			acountRepository
					.save(new Compte(2, "esdd", "Courant", "2004-3-3", 190000.0, client));
			Client client2 = clientRepository
					.save(new Client(2,"MIWONOUKO","juste","2020-8-3", "Masculin","Sagbado rue5","+228 98753241","justemiwonouko@gmail.com","Allemande",null));
			acountRepository
					.save(new Compte(3,"dums","Courant","2004-3-3",987000.0,client2));
			Client client3 = clientRepository
					.save(new Client(3,"KPESSEA","justine","2020-8-3", "Feminin","Kpalime rue8","+228 93453241","justinekpessea@gmail.com","Norvegienne",null));
			acountRepository
					.save(new Compte(4,"dorime","Epargne","2004-3-3",36000.0,client3));


		};
	}

}
