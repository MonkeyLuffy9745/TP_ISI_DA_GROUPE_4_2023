package TP.SI.tpIntegration.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TP.SI.tpIntegration.entities.Compte;
import TP.SI.tpIntegration.repositories.CompteRepository;

@Service
public class ServiceCompte {

        @Autowired
        private CompteRepository compteRepository;
    
        public List<Compte> getAll() {
            return compteRepository.findAll();
        }
    
        public Compte get(Integer id) {
            return compteRepository.findById(id).orElse(null);
        }
    
        public Compte save(Compte compte) {
            return compteRepository.save(compte);
        }
    
        public void delete(Integer id) {
            compteRepository.deleteById(id);
        }  
}
