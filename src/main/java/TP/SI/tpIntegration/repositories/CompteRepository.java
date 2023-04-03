package TP.SI.tpIntegration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import TP.SI.tpIntegration.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}