package TP.SI.tpIntegration.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import TP.SI.tpIntegration.services.ServiceClient;
import TP.SI.tpIntegration.entities.Client;


@RestController
public class ClientController {
    @Autowired
    private ServiceClient serviceClient;

    @GetMapping("/clients")
    public List<Client> getAll() {
        return serviceClient.getAll();
    }

    @GetMapping("/clients/{id}")
    public Client get(@PathVariable Integer id) {
        return serviceClient.get(id);
    }

    @PostMapping("/clients")
    public Client save(@RequestBody Client client) {
        return serviceClient.save(client);
    }

    @PutMapping("/clients/{id}")
    public Client update(@PathVariable Integer id, @RequestBody Client client) {
        client.setId(id);
        return serviceClient.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Integer id) {
        serviceClient.delete(id);
    }
}