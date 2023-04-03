package TP.SI.tpIntegration.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import TP.SI.tpIntegration.entities.Client;
import TP.SI.tpIntegration.entities.Compte;
import TP.SI.tpIntegration.services.ServiceClient;
import TP.SI.tpIntegration.services.ServiceCompte;

@RestController
public class CompteController {
    @Autowired
    private ServiceCompte serviceCompte;
    @Autowired
    private ServiceClient serviceClient;

    @GetMapping("/comptes")
    public List<Compte> getAll() {
        return serviceCompte.getAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte get(@PathVariable Integer id) {
        return serviceCompte.get(id);
    }

    @PostMapping("/comptes")
    public Compte save(@RequestParam("clientId") Integer clientId, @RequestBody Compte compte) {
        Client client = serviceClient.get(clientId);
        compte.setSolde(0.0);
        compte.setClient(client);
        return serviceCompte.save(compte);
    }

    @PutMapping("/comptes/depot/{id}")
    public Compte Depot(@PathVariable Integer id, @RequestParam ("montant") Double montant) {
        Compte compte = serviceCompte.get(id);
        if (compte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de Compte Trouvé");
        }
        compte.setSolde(compte.getSolde()+montant);
        return serviceCompte.save(compte);
    }


    @PutMapping("/comptes/retrait/{id}")
    public Compte retrait( @PathVariable Integer id, @RequestParam( "montant") Double montant){
        Compte comptes = serviceCompte.get(id);
        if(comptes.getSolde()< montant){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Montant Insuffisant");
        }
        comptes.setSolde(comptes.getSolde()-montant);
        return serviceCompte.save(comptes);
    }

    @PutMapping("/comptes/transfert")
    public List<Compte> transfert(@RequestParam("idCompteEnvoyeur") Integer idCompteEnvoyeur,
            @RequestParam("idCompteReceveur") Integer idCompteReceveur, @RequestParam("montant") Double montant) {

        Compte CompteEnvoyeur = serviceCompte.get(idCompteEnvoyeur);
        if (CompteEnvoyeur == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte de l'envoyeur n'est trouvé");
        }
        Compte compteReceveur = serviceCompte.get(idCompteReceveur);
        if (compteReceveur == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte du destinataire n'est pas trouvé");
        }
        CompteEnvoyeur.setSolde(CompteEnvoyeur.getSolde() - montant);
        compteReceveur.setSolde(compteReceveur.getSolde() + montant);
        serviceCompte.save(CompteEnvoyeur);
        serviceCompte.save(compteReceveur);
        List<Compte> comptes = new ArrayList<>();
        comptes.add(CompteEnvoyeur);
        comptes.add(compteReceveur);
        return comptes;
    }

    @PutMapping("/comptes/{id}")
    public Compte update(@PathVariable Integer id, @RequestParam ("clientId") Integer clientId,@RequestBody Compte compte) {
        Compte comptes = serviceCompte.get(id);
        Client client = serviceClient.get(clientId);
        comptes.setClient(client);
        if (compte.getType() != null){
            comptes.setType(compte.getType());
        }
        if(compte.getDateCreation()!= null){
            comptes.setDateCreation(compte.getDateCreation());
        }
        comptes.setSolde(compte.getSolde());
        return serviceCompte.save(comptes);
    }



    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Integer id) {
        serviceCompte.delete(id);
    }
}   
