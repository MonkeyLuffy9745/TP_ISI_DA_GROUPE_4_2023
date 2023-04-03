package TP.SI.tpIntegration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import TP.SI.tpIntegration.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Integer> {

}